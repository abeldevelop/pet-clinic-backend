package com.abeldevelop.petclinic.library.common.config;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PropertyLogger  {


	@EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        final Environment env = event.getApplicationContext().getEnvironment();
        log.info("====== Environment and Configuration ======");
        log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        Iterator<PropertySource<?>> iterator = sources.iterator();
        while(iterator.hasNext()) {
        	PropertySource<?> propertySource = iterator.next();
        	if(propertySource instanceof EnumerablePropertySource) {
        		@SuppressWarnings("rawtypes")
				EnumerablePropertySource enumerablePropertySource = (EnumerablePropertySource) propertySource;
    			log.info("PropertySource: {}", enumerablePropertySource.getName());
        		for(String property : Arrays.asList(enumerablePropertySource.getPropertyNames())) {
        			log.info("{}: {}", property, env.getProperty(property));
        		}
        	}
        }
        log.info("===========================================");
    }
}