package at.appicenter.ddd.spring;

import at.appicenter.ddd.spring.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.test.context.junit.jupiter.*;

@SpringJUnitConfig(BasePackageTest.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@EnableApplicationServices(basePackages = "at.appicenter.ddd.spring.services")
@EnableDomainServices(basePackageClasses = DummyDomainService.class)
class BasePackageTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void beans_must_be_instantiated() {
        Assertions.assertNotNull(applicationContext.getBean(DummyApplicationService.class));
        Assertions.assertNotNull(applicationContext.getBean(DummyDomainService.class));
    }
}