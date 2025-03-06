//package com.ruoyi.job.config;
//
//import java.util.Properties;
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
///**
// * Scheduled Task Configuration (For single-machine deployment, it is recommended to delete this class and qrtz database tables, using memory by default will be most efficient)
// * 
// * @author ruoyi
// */
//@Configuration
//public class ScheduleConfig
//{
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource)
//    {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setDataSource(dataSource);
//
//        // Quartz parameters
//        Properties prop = new Properties();
//        prop.put("org.quartz.scheduler.instanceName", "RuoyiScheduler");
//        prop.put("org.quartz.scheduler.instanceId", "AUTO");
//        // Thread pool configuration
//        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
//        prop.put("org.quartz.threadPool.threadCount", "20");
//        prop.put("org.quartz.threadPool.threadPriority", "5");
//        // JobStore configuration
//        prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");
//        // Cluster configuration
//        prop.put("org.quartz.jobStore.isClustered", "true");
//        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
//        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "10");
//        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
//
//        // Enable for SQL Server
//        // prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
//        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
//        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
//        factory.setQuartzProperties(prop);
//
//        factory.setSchedulerName("RuoyiScheduler");
//        // Delayed start
//        factory.setStartupDelay(1);
//        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
//        // Optional, QuartzScheduler
//        // Update existing Jobs at startup, so you don't need to delete the corresponding record in qrtz_job_details table every time you modify targetObject
//        factory.setOverwriteExistingJobs(true);
//        // Set auto-startup, default is true
//        factory.setAutoStartup(true);
//
//        return factory;
//    }
//}
