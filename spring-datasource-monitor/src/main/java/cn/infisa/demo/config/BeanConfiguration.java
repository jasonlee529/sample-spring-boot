package cn.infisa.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.transaction.*;

/**
 * Created by quinn on 28/06/2018.
 **/
@Configuration
public class BeanConfiguration {
    @Autowired
    private DataSource dataSource;

    @Bean
    public RestHandleExceptionResolver getRestHandleExceptionResolver() {
        return new RestHandleExceptionResolver();
    }

    @Bean
    public SimpleCORSFilter getSimpleCORSFilter() {
        return new SimpleCORSFilter();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;

    }
}
