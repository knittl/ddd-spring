package at.appicenter.ddd.spring;

import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;
import org.springframework.core.env.*;
import org.springframework.core.type.*;
import org.springframework.core.type.filter.*;
import org.springframework.util.*;

import java.lang.annotation.*;
import java.util.*;

/**
 * Utility class to register bean definitions.
 */
final class ServiceImportRegistrarUtil {
    private static final BeanNameGenerator BEAN_NAME_GENERATOR = AnnotationBeanNameGenerator.INSTANCE;

    private ServiceImportRegistrarUtil() {
    }

    /**
     * Registers all bean definitions for classes annotated with componentAnnotations.
     *
     * @param metadata annotation metadata of the importing class
     * @param registry current bean definition registry
     * @param environment the current environment
     * @param enableAnnotation the {@code Enable*} annotation which defines the classpath scanning roots
     * @param componentAnnotations scanned classes with this annotation will be registered as bean
     */
    public static void registerBeanDefinitions(
            final AnnotationMetadata metadata,
            final BeanDefinitionRegistry registry,
            final Environment environment,
            final Class<? extends Annotation> enableAnnotation,
            final Collection<Class<? extends Annotation>> componentAnnotations) {
        final AnnotationAttributes annotationAttributes = new AnnotationAttributes(
                metadata.getAnnotationAttributes(enableAnnotation.getCanonicalName()));

        final ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false, environment);
        for (final Class<? extends Annotation> componentAnnotation : componentAnnotations) {
            provider.addIncludeFilter(new AnnotationTypeFilter(componentAnnotation, true));
        }

        final Set<String> basePackages
                = getBasePackages((StandardAnnotationMetadata) metadata, annotationAttributes);

        for (final String basePackage : basePackages) {
            for (final BeanDefinition beanDefinition : provider.findCandidateComponents(basePackage)) {
                final String beanClassName = BEAN_NAME_GENERATOR.generateBeanName(beanDefinition, registry);
                if (!registry.containsBeanDefinition(beanClassName)) {
                    registry.registerBeanDefinition(beanClassName, beanDefinition);
                }
            }
        }
    }

    private static Set<String> getBasePackages(
            final StandardAnnotationMetadata metadata,
            final AnnotationAttributes attributes) {
        final String[] basePackages = attributes.getStringArray("basePackages");
        final Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        final Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        for (final Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }

        if (packagesToScan.isEmpty()) {
            // If value attribute is not set, fallback to the package of the annotated class
            return Collections.singleton(metadata.getIntrospectedClass().getPackage().getName());
        }

        return packagesToScan;
    }
}
