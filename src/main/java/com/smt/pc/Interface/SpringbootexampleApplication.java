package com.smt.pc.Interface;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@ServletComponentScan
//启注解事务管理
@EnableTransactionManagement
@EnableRedisHttpSession
@MapperScan("com.smt.pc.Interface.mapper")
public class SpringbootexampleApplication{

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	// 文件上传限制
//	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件大小限制 ,超出设置页面会抛出异常信息，
		// 这样在文件上传的地方就需要进行异常信息的处理了;
		factory.setMaxFileSize("2560KB"); // KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("5120KB");
		// Sets the directory location where files will be stored.
		// factory.setLocation("路径地址");
		return factory.createMultipartConfig();
	}

	//文件下载
	@Bean
	public HttpMessageConverters restFileDownloadSupport() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		return new HttpMessageConverters(arrayHttpMessageConverter);
	}

    // HTTP请求监听事件
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	 /**
     * 跨域过滤器
     * @return
     */
//    @Bean
    //  public FilterRegistrationBean corsFilter() {
     //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      //    CorsConfiguration config = new CorsConfiguration();
       //   config.addAllowedOrigin("*");
       //   config.setAllowCredentials(true);
       //   config.addAllowedHeader("*");
       //   config.addAllowedMethod("*");
        //  source.registerCorsConfiguration("/**", config);

        //  FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //  bean.setOrder(0);
        //  return bean;
    //  }


	 //@Bean
	 public EmbeddedServletContainerFactory servletContainer() {

		 TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

			 @Override
			 protected void postProcessContext(Context context) {

				 SecurityConstraint securityConstraint = new SecurityConstraint();
				 securityConstraint.setUserConstraint("CONFIDENTIAL");
				 SecurityCollection collection = new SecurityCollection();
				 collection.addPattern("/*");
				 securityConstraint.addCollection(collection);
				 context.addConstraint(securityConstraint);
			 }
		 };
		 tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		 return tomcat;
	 }

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(80);
		connector.setRedirectPort(443);
		connector.setSecure(false);
		return connector;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootexampleApplication.class, args);
	}
}
