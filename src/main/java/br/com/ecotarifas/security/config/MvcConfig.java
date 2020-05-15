package br.com.ecotarifas.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/view").setViewName("view");
        registry.addViewController("/").setViewName("view");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(
				"/css/**",
				"/fonts/**",
				"/img/**",
				"/js/**",
				"/vendor/**")
		.addResourceLocations(
				"classpath:/static/css/",
				"classpath:/static/fonts/",
				"classpath:/static/img/",
				"classpath:/static/js/",
				"classpath:/static/vendor/");
	}
}