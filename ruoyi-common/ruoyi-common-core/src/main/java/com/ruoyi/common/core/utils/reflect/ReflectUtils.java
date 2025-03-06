package com.ruoyi.common.core.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.DateUtils;

/**
 * Reflection utility class. Provides tools for calling getter/setter methods, accessing private variables,
 * calling private methods, getting generic type Class, getting the real class that has been AOP processed, etc.
 *
 * @author ruoyi
 */
@SuppressWarnings("rawtypes")
public class ReflectUtils
{
    private static final String SETTER_PREFIX = "set";

    private static final String GETTER_PREFIX = "get";

    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * Call Getter method.
     * Supports multiple levels, e.g.: object.object.method
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeGetter(Object obj, String propertyName)
    {
        Object object = obj;
        for (String name : StringUtils.split(propertyName, "."))
        {
            String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
            object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
        }
        return (E) object;
    }

    /**
     * Call Setter method, only match method name.
     * Supports multiple levels, e.g.: object.object.method
     */
    public static <E> void invokeSetter(Object obj, String propertyName, E value)
    {
        Object object = obj;
        String[] names = StringUtils.split(propertyName, ".");
        for (int i = 0; i < names.length; i++)
        {
            if (i < names.length - 1)
            {
                String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
                object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
            }
            else
            {
                String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
                invokeMethodByName(object, setterMethodName, new Object[] { value });
            }
        }
    }

    /**
     * Directly read object property value, ignoring private/protected modifiers, bypassing getter function.
     */
    @SuppressWarnings("unchecked")
    public static <E> E getFieldValue(final Object obj, final String fieldName)
    {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null)
        {
            logger.debug("Field [" + fieldName + "] not found in [" + obj.getClass() + "]");
            return null;
        }
        E result = null;
        try
        {
            result = (E) field.get(obj);
        }
        catch (IllegalAccessException e)
        {
            logger.error("Impossible exception thrown {}", e.getMessage());
        }
        return result;
    }

    /**
     * Directly set object property value, ignoring private/protected modifiers, bypassing setter function.
     */
    public static <E> void setFieldValue(final Object obj, final String fieldName, final E value)
    {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null)
        {
            logger.debug("Field [" + fieldName + "] not found in [" + obj.getClass() + "]");
            return;
        }
        try
        {
            field.set(obj, value);
        }
        catch (IllegalAccessException e)
        {
            logger.error("Impossible exception thrown: {}", e.getMessage());
        }
    }

    /**
     * Directly call object method, ignoring private/protected modifiers.
     * For one-time call situations, otherwise use getAccessibleMethod() to get Method for repeated calls.
     * Matches both method name and parameter types.
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
            final Object[] args)
    {
        if (obj == null || methodName == null)
        {
            return null;
        }
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null)
        {
            logger.debug("Method [" + methodName + "] not found in [" + obj.getClass() + "]");
            return null;
        }
        try
        {
            return (E) method.invoke(obj, args);
        }
        catch (Exception e)
        {
            String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
            throw convertReflectionExceptionToUnchecked(msg, e);
        }
    }

    /**
     * Directly call object method, ignoring private/protected modifiers.
     * For one-time call situations, otherwise use getAccessibleMethodByName() to get Method for repeated calls.
     * Only matches function name, if multiple functions have the same name, calls the first one.
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeMethodByName(final Object obj, final String methodName, final Object[] args)
    {
        Method method = getAccessibleMethodByName(obj, methodName, args.length);
        if (method == null)
        {
            logger.debug("Method [" + methodName + "] not found in [" + obj.getClass() + "]");
            return null;
        }
        try
        {
            // Type conversion (convert parameter data type to target method parameter type)
            Class<?>[] cs = method.getParameterTypes();
            for (int i = 0; i < cs.length; i++)
            {
                if (args[i] != null && !args[i].getClass().equals(cs[i]))
                {
                    if (cs[i] == String.class)
                    {
                        args[i] = Convert.toStr(args[i]);
                        if (StringUtils.endsWith((String) args[i], ".0"))
                        {
                            args[i] = StringUtils.substringBefore((String) args[i], ".0");
                        }
                    }
                    else if (cs[i] == Integer.class)
                    {
                        args[i] = Convert.toInt(args[i]);
                    }
                    else if (cs[i] == Long.class)
                    {
                        args[i] = Convert.toLong(args[i]);
                    }
                    else if (cs[i] == Double.class)
                    {
                        args[i] = Convert.toDouble(args[i]);
                    }
                    else if (cs[i] == Float.class)
                    {
                        args[i] = Convert.toFloat(args[i]);
                    }
                    else if (cs[i] == Date.class)
                    {
                        if (args[i] instanceof String)
                        {
                            args[i] = DateUtils.parseDate(args[i]);
                        }
                        else
                        {
                            args[i] = DateUtil.getJavaDate((Double) args[i]);
                        }
                    }
                    else if (cs[i] == boolean.class || cs[i] == Boolean.class)
                    {
                        args[i] = Convert.toBool(args[i]);
                    }
                }
            }
            return (E) method.invoke(obj, args);
        }
        catch (Exception e)
        {
            String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
            throw convertReflectionExceptionToUnchecked(msg, e);
        }
    }

    /**
     * Loop upward type conversion, get object's DeclaredField, and force set as accessible.
     * If still not found after converting up to Object, return null.
     */
    public static Field getAccessibleField(final Object obj, final String fieldName)
    {
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(fieldName, "fieldName can't be blank");
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            }
            catch (NoSuchFieldException e)
            {
                continue;
            }
        }
        return null;
    }

    /**
     * Loop upward type conversion, get object's DeclaredMethod, and force set as accessible.
     * If still not found after converting up to Object, return null.
     * Matches function name + parameter types.
     * For methods that need to be called multiple times. First use this function to get Method,
     * then call Method.invoke(Object obj, Object... args)
     */
    public static Method getAccessibleMethod(final Object obj, final String methodName,
            final Class<?>... parameterTypes)
    {
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(methodName, "methodName can't be blank");
        for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass())
        {
            try
            {
                Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
                makeAccessible(method);
                return method;
            }
            catch (NoSuchMethodException e)
            {
                continue;
            }
        }
        return null;
    }

    /**
     * Loop upward type conversion, get object's DeclaredMethod, and force set as accessible.
     * If still not found after converting up to Object, return null.
     * Only matches function name.
     * For methods that need to be called multiple times. First use this function to get Method,
     * then call Method.invoke(Object obj, Object... args)
     */
    public static Method getAccessibleMethodByName(final Object obj, final String methodName, int argsNum)
    {
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(methodName, "methodName can't be blank");
        for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass())
        {
            Method[] methods = searchType.getDeclaredMethods();
            for (Method method : methods)
            {
                if (method.getName().equals(methodName) && method.getParameterTypes().length == argsNum)
                {
                    makeAccessible(method);
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * Change private/protected method to public, try to avoid calling actual modification statements
     * to prevent JDK's SecurityManager from complaining.
     */
    public static void makeAccessible(Method method)
    {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
                && !method.isAccessible())
        {
            method.setAccessible(true);
        }
    }

    /**
     * Change private/protected member variable to public, try to avoid calling actual modification statements
     * to prevent JDK's SecurityManager from complaining.
     */
    public static void makeAccessible(Field field)
    {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                || Modifier.isFinal(field.getModifiers())) && !field.isAccessible())
        {
            field.setAccessible(true);
        }
    }

    /**
     * Through reflection, get the generic parameter type declared in Class definition,
     * note that generics must be defined in the parent class.
     * If not found, return Object.class.
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassGenricType(final Class clazz)
    {
        return getClassGenricType(clazz, 0);
    }

    /**
     * Through reflection, get the generic parameter type of the parent class declared in Class definition.
     * If not found, return Object.class.
     */
    public static Class getClassGenricType(final Class clazz, final int index)
    {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType))
        {
            logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0)
        {
            logger.debug("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class))
        {
            logger.debug(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    public static Class<?> getUserClass(Object instance)
    {
        if (instance == null)
        {
            throw new RuntimeException("Instance must not be null");
        }
        Class clazz = instance.getClass();
        if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR))
        {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass))
            {
                return superClass;
            }
        }
        return clazz;
    }

    /**
     * Convert checked exceptions in reflection to unchecked exceptions.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(String msg, Exception e)
    {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException)
        {
            return new IllegalArgumentException(msg, e);
        }
        else if (e instanceof InvocationTargetException)
        {
            return new RuntimeException(msg, ((InvocationTargetException) e).getTargetException());
        }
        return new RuntimeException(msg, e);
    }
}
