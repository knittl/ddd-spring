package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.test.context.junit.jupiter.*;

@SpringJUnitConfig(EnableDomainServicesTest.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@EnableDomainServices
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
}