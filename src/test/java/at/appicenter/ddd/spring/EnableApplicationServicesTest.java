package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.test.context.junit.jupiter.*;

@SpringJUnitConfig(EnableApplicationServicesTest.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@EnableApplicationServices
class EnableApplicationServicesTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void application_services_must_exist_as_bean() {
        // only application service beans should be instantiated
        Assertions.assertNotNull(applicationContext.getBean(DummyApplicationService.class));
    }

    @Test
    void domain_services_must_not_exist_as_bean() {
        // only application service beans should be instantiated
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean(DummyDomainService.class));
    }
}