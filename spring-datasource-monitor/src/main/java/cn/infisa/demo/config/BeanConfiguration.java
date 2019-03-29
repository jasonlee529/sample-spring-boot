package cn.infisa.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by quinn on 28/06/2018.
 **/
@Configuration
public class BeanConfiguration {

    @Bean
    public RestHandleExceptionResolver getRestHandleExceptionResolver() {
        return new RestHandleExceptionResolver();
    }

    @Bean
    public SimpleCORSFilter getSimpleCORSFilter() {
        return new SimpleCORSFilter();
    }
}
