package com.innowise.rm.calendar.app.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcRegistrations webMvcRegistrationsHandlerMapping() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return requestMappingHandlerMapping();
            }

        };
    }

    private RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {
            private static final String API_BASE_PATH = "api/v1";
            @Override
            protected void registerHandlerMethod(final Object handler,
                                                 final Method method, final RequestMappingInfo mapping) {
                Class<?> beanType = method.getDeclaringClass();
                RequestMappingInfo newMapping = mapping;
                if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
                    PatternsRequestCondition apiPattern = new PatternsRequestCondition(API_BASE_PATH)
                            .combine(mapping.getPatternsCondition());
                    newMapping = new RequestMappingInfo(mapping.getName(), apiPattern,
                            mapping.getMethodsCondition(), mapping.getParamsCondition(),
                            mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                            mapping.getProducesCondition(), mapping.getCustomCondition());
                }
                super.registerHandlerMethod(handler, method, newMapping);
            }
        };
    }

}
