package at.appicenter.ddd.spring;

import at.appicenter.ddd.annotations.*;
import org.springframework.beans.factory.support.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;
import org.springframework.core.env.*;
import org.springframework.core.type.*;

import java.lang.annotation.*;
import java.util.*;

/**
 * Enable detection of DDD domain services.
 * <p>
 * Each class annotated with {@link DomainService @DomainService} will be automatically added as a bean to the
 * application context.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableDomainServices.Registrar.class)
public @interface EnableDomainServices {
    /**
     * Alias for {@link #basePackages}.
     * <p>
     * Allows for more concise annotation declarations if no other attributes are needed; for example,
     * {@code @EnableDomainServices("org.my.pkg")} instead of
     * {@code @EnableDomainServices(basePackages = "org.my.pkg")}.
     */
    @AliasFor(attribute = "basePackages")
    String[] value() default {};

    /**
     * Base packages to scan for {@link DomainService Domain Services}.
     * <p>
     * {@link #value} is an alias for (and mutually exclusive with) this attribute.
     * <p>
     * Use {@link #basePackageClasses} for a type-safe alternative to String-based package names.
     */
    @AliasFor(attribute = "value")
    String[] basePackages() default {};

    /**
     * Type-safe alternative to {@link #basePackages} for specifying the packages to scan for
     * {@link DomainService Domain Services}. The package of each class specified will be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that serves no purpose other than
     * being referenced by this attribute.
     */
    Class<?>[] basePackageClasses() default {};

    class Registrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
        private Environment environment;

        @Override
        public void setEnvironment(final Environment environment) {
            this.environment = environment;
        }

        @Override
        public void registerBeanDefinitions(final AnnotationMetadata metadata, final BeanDefinitionRegistry registry) {
            ServiceImportRegistrarUtil.registerBeanDefinitions(
                    metadata,
                    registry,
                    environment,
                    EnableDomainServices.class,
                    Collections.singleton(DomainService.class));
        }
    }
}
