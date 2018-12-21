package com.lm.shopping.common;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.util.List;

@Singleton
public class AppInjectionResolver implements JustInTimeInjectionResolver {

    @Inject private ServiceLocator serviceLocator;

    @Override
    public boolean justInTimeResolution(Injectee injectee) {
        final Type requiredType = injectee.getRequiredType();
        if (injectee.getRequiredQualifiers().isEmpty() && requiredType instanceof Class) {
            final Class<?> requiredClass = (Class<?>) requiredType;
            if (requiredClass.getName().startsWith("com.lm.shopping")) {
                final List<ActiveDescriptor<?>> descriptors = ServiceLocatorUtilities.addClasses(serviceLocator, requiredClass);
                return !descriptors.isEmpty();
            }
        }
        return false;
    }
} 