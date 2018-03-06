package com.ngoc.bookmanagement.configuration;

import com.ngoc.bookmanagement.Interceptor.AuthenticateInterceptor;
import com.ngoc.bookmanagement.Interceptor.BookInterceptor;
import com.ngoc.bookmanagement.Interceptor.LoginInterceptor;
import com.ngoc.bookmanagement.Interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc // mvc:annotation-driven
@Configuration
@ComponentScan(basePackages = { "com.ngoc.bookmanagement"})
public class SpringWebConfig implements WebMvcConfigurer{
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
    }

    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(new AuthenticateInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/about")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/registration")
                .excludePathPatterns("/duplicateEmailException")
                .excludePathPatterns("/createDummyData")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/login")
                .addPathPatterns("/registration");

        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/user/{id}")
                .addPathPatterns("/user/{id}/lock")
                .addPathPatterns("/user/{id}/unlock")
                .addPathPatterns("/user/{id}/Change-Password");

        registry.addInterceptor(new BookInterceptor())
                .addPathPatterns("/book/{id}/edit")
                .addPathPatterns("/book/{id}")
                .excludePathPatterns("/book/new")
                .excludePathPatterns("/book/search");
    }

}