package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomBeanNameTest.Config.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomBeanNameTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void custom_bean_name_must_be_set() {
        // bean must be found with custom bean name
        Assertions.assertNotNull(applicationContext.getBean("customApplicationServiceBeanName"));
    }

    @Test
    void default_bean_name_must_not_be_set() {
        // bean must not be found with default/generated bean name
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("dummyApplicationService"));
    }

    @Configuration
    @EnableApplicationServices
    public static class Config {
    }
}