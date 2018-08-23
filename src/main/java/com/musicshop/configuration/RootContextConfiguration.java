package com.musicshop.configuration;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.musicshop.shoppingcart.ShoppingCart;

@Configuration
@EnableWebMvc
@ComponentScan("com.musicshop")
@PropertySource({ "classpath:connections.properties" })
@EnableScheduling
public class RootContextConfiguration implements WebMvcConfigurer {

	@Autowired
	Environment environment;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	@SessionScope
	public ShoppingCart getShoppingCart() {
		return new ShoppingCart();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public JavaMailSender getJavaMailSender() {

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
		properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(environment.getProperty("mail.smtp.host"));
		javaMailSender.setPort(Integer.valueOf(environment.getProperty("mail.smtp.port")));
		javaMailSender.setUsername(environment.getProperty("mail.server.username"));
		javaMailSender.setPassword(environment.getProperty("mail.server.password"));

		javaMailSender.setJavaMailProperties(properties);
		return javaMailSender;

//			final String username = "ijankovic47@gmail.com";
//			final String password = "";
		//
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp.gmail.com");
//			props.put("mail.smtp.port", "587");
		//
//			Session session = Session.getInstance(props,
//			  new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, password);
//				}
//			  });
//			return session;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH").allowedOrigins("*")
				.allowedHeaders("*");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}
}
