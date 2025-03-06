package com.ruoyi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;

/**
 * Authentication and authorization center
 *
 * @author ruoyi
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RuoYiAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Authentication and authorization center started successfully   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
