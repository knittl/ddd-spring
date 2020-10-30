package at.appicenter.ddd.spring;

import org.springframework.core.annotation.*;

import java.lang.annotation.*;

/**
 * Convenience annotation for DDD applications.
 * <p>
 * Enables domain and application services.
 */
@Retention(RetentionPolicy.RUNTIME)
@EnableApplicationServices
@EnableDomainServices
public @interface DomainDrivenApplication {
    /**
     * Type-safe alternative to {@link #scanApplicationServices} for specifying the packages to scan for
     * {@link at.appicenter.ddd.annotations.ApplicationService Application Services}. The package of each class
     * specified will be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that serves no purpose other than
     * being referenced by this attribute.
     *
     * @return packages to scan
     */
    @AliasFor(annotation = EnableApplicationServices.class, attribute = "basePackageClasses")
    Class<?>[] scanApplicationServiceClasses() default {};

    /**
     * Base packages to scan for {@link at.appicenter.ddd.annotations.ApplicationService Application Services}. Use
     * {@link #scanApplicationServiceClasses} for a type-safe alternative to String-based package names.
     *
     * @return packages to scan
     */
    @AliasFor(annotation = EnableApplicationServices.class, attribute = "basePackages")
    String[] scanApplicationServices() default {};

    /**
     * Type-safe alternative to {@link #scanDomainServices} for specifying the packages to scan for
     * {@link at.appicenter.ddd.annotations.DomainService Domain Services}. The package of each class specified will
     * be scanned.
     * <p>
     * Consider creating a special no-op marker class or interface in each package that serves no purpose other than
     * being referenced by this attribute.
     *
     * @return packages to scan
     */
    @AliasFor(annotation = EnableDomainServices.class, attribute = "basePackageClasses")
    Class<?>[] scanDomainServiceClasses() default {};

    /**
     * Base packages to scan for {@link at.appicenter.ddd.annotations.DomainService Domain Services}. Use
     * {@link #scanDomainServiceClasses} for a type-safe alternative to String-based package names.
     *
     * @return packages to scan
     */
    @AliasFor(annotation = EnableDomainServices.class, attribute = "basePackages")
    String[] scanDomainServices() default {};
}
