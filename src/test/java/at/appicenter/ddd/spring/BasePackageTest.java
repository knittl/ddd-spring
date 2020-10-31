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
@ContextConfiguration(classes = BasePackageTest.Config.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BasePackageTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void beans_must_be_instantiated() {
        Assertions.assertNotNull(applicationContext.getBean(DummyApplicationService.class));
        Assertions.assertNotNull(applicationContext.getBean(DummyDomainService.class));
    }

    @Configuration
    @EnableApplicationServices(basePackages = "at.appicenter.ddd.spring.services")
    @EnableDomainServices(basePackageClasses = DummyDomainService.class)
    public static class Config {
    }
}