package cn.zzy.library_web.config;

import cn.zzy.library_web.filter.FilterDemo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterDemo filterDemo(){
        return new FilterDemo();
    }
    @Bean
    public FilterRegistrationBean registFilter(FilterDemo filterDemo) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filterDemo);
        registration.addUrlPatterns("/login///");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }

}