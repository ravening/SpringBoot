package com.rakeshv.springbootvuejs.config;

import com.rakeshv.springbootvuejs.models.Course;
import com.rakeshv.springbootvuejs.models.Todo;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class RestRepositoryConfigurator implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Todo.class);
        config.exposeIdsFor(Course.class);
    }
}
