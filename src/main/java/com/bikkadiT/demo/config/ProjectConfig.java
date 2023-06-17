package com.bikkadiT.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.lang.model.element.ModuleElement;
@Configuration
public class ProjectConfig {
    @Bean
    public ModelMapper mapper() {

        return new ModelMapper();
    }

}
