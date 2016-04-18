package com.raj.springcrud.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.raj.springcrud.custom.Crypto;
import com.raj.springcrud.dao.BookDao;
import com.raj.springcrud.dao.BookDaoImpl;
import com.raj.springcrud.models.Book;

/**
 * @author rajkumarj
 *
 */
@Configuration
@ComponentScan("com.raj.springcrud")
@EnableTransactionManagement
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureViewResolvers(org.springframework.web.servlet.config.annotation.ViewResolverRegistry)
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/webview/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

	@Bean(name = "dataSource")
	public DataSource getDataSource() throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
		dataSource.setUsername("root");
		Crypto td = new Crypto();
		// Add Mysql Password here. You can Encrypt password using Crypto.encrypt("yourpassd") 
		//and give the encrypted password here
		dataSource.setPassword(td.decrypt("24HJ71RSyh4/Qyf5xPzTjg=="));

		return dataSource;
	}
	
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Book.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	
	@Autowired
    @Bean(name = "bookDao")
    public BookDao getUserDao(SessionFactory sessionFactory) {
    	return new BookDaoImpl(sessionFactory);
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
