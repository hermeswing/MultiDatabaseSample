package multi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringRestApiApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringRestApiApplication.class, args);

        ApplicationContext applicationContext = SpringApplication.run(SpringRestApiApplication.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            log.debug("ApplicationContext Beans Names :: {}", name);
        }
    }

}
