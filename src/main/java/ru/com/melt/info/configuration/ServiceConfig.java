package ru.com.melt.info.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ComponentScan({"ru.com.melt.info.service.impl",
        "ru.com.melt.info.controller",
        "ru.com.melt.info.filter",
        "ru.com.melt.info.component.impl"})
@EnableAspectJAutoProxy
public class ServiceConfig {

    @Bean
    public PropertiesFactoryBean properties() {
        PropertiesFactoryBean properties = new PropertiesFactoryBean();
        properties.setLocation(new ClassPathResource("logic.properties"));
        return properties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocations(getResources());
        return conf;
    }

    private static Resource[] getResources() {
        return new Resource[]{
                new ClassPathResource("logic.properties"),
                new ClassPathResource("properties/application.properties"),
                new ClassPathResource("properties/elasticsearch.properties")
        };
    }
}
