package bgroup.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "bgroup.configuration" })
@PropertySource(value = { "classpath:jdbc.properties" })
public class HibernateConfigurationOnline {

    @Autowired
    private Environment environment;

    @Bean(name = "sessionFactoryOnline")
    public LocalSessionFactoryBean sessionFactoryOnline() {
        LocalSessionFactoryBean sessionFactoryOnline = new LocalSessionFactoryBean();
        sessionFactoryOnline.setDataSource(dataSourceOnline());
        sessionFactoryOnline.setPackagesToScan(new String[] { "bgroup.model" });
        sessionFactoryOnline.setHibernateProperties(hibernatePropertiesOnline());
        return sessionFactoryOnline;
     }
	
    @Bean(name="dataSourceOnline")
    public DataSource dataSourceOnline() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassNameOnline"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url.online"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username.online"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password.online"));
        return dataSource;
    }
    
    private Properties hibernatePropertiesOnline() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect.online"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql.online"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql.online"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.online"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManagerOnline(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

