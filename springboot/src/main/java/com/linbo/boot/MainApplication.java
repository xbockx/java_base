package com.linbo.boot;

import com.linbo.boot.bean.Pet;
import com.linbo.boot.bean.User;
import com.linbo.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 这是一个Spring Boot应用
 */

//MainApplication所在包及其子包都会默认被扫描
//@SpringBootApplication(scanBasePackages = "com.linbo")    // 修改扫描包， 方式一
//@ComponentScan("com.linbo")       // 方式二
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 返回所有IoC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        // 打印所有组件名
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        // 从容器中获取组件
        Pet t1 = run.getBean("myTom", Pet.class);
        Pet t2 = run.getBean("myTom", Pet.class);
        System.out.println("组件相同：" + (t1 == t2));

        // com.linbo.boot.config.MyConfig$$EnhancerBySpringCGLIB$$75444e20@5baaae4c
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        // 如果@Configuration(proxyBeanMethods = true)，代理对象调用方法
        // Spring Boot 总会检查容器内组件是否存在以保持组件单例
        User u1 = bean.user02();
        User u2 = bean.user02();
        System.out.println("调用内部方法返回组件相同：" + (u1 == u2));

        User myUser = run.getBean("myUser", User.class);
        Pet myTom = run.getBean("myTom", Pet.class);
        System.out.println("用户的宠物是都是容器中宠物：" + (myUser.getPet() == myTom));
    }
}
