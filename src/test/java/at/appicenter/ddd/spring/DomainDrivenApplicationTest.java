package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DomainDrivenApplicationTest.Config.class)
class DomainDrivenApplicationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void ddd_services_must_exist_as_bean() {
        Assertions.assertNotNull(applicationContext.getBean(DummyDomainService.class));
        Assertions.assertNotNull(applicationContext.getBean(DummyApplicationService.class));
    }

    @Configuration
    @DomainDrivenApplication
    public static class Config {
    }
}