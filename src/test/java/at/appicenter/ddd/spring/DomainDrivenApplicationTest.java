package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.test.context.junit.jupiter.*;

@SpringJUnitConfig(DomainDrivenApplicationTest.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DomainDrivenApplication
class DomainDrivenApplicationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void ddd_services_must_exist_as_bean() {
        Assertions.assertNotNull(applicationContext.getBean(DummyDomainService.class));
        Assertions.assertNotNull(applicationContext.getBean(DummyApplicationService.class));
    }
}