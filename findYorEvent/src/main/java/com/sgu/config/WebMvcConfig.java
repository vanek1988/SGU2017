package com.sgu.config;

/**
 * Created by admin on 24.06.2017.
 */   import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration

public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/base").setViewName("base");
        registry.addViewController("/").setViewName("base");
        registry.addViewController("/hello").setViewName("hello");
    }


}
