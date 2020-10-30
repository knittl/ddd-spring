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
@ContextConfiguration(classes = EnableDomainServicesTest.Config.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EnableDomainServicesTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void domain_services_must_exist_as_bean() {
        // only domain service beans should be instantiated
        Assertions.assertNotNull(applicationContext.getBean(DummyDomainService.class));
    }

    @Test
    void application_services_must_not_exist_as_bean() {
        // only domain service beans should be instantiated
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean(DummyApplicationService.class));
    }

    @Configuration
    @EnableDomainServices
    public static class Config {
    }
}