package com.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableAsync
public class SpringConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

//	@Override
//	public Class<? extends Feature> getFeatureClass() {
//		return AppFeaturesEnum.class;
//	}
//
//	@Override
//	public StateRepository getStateRepository() {
//		return new FileBasedStateRepository(new File("features.properties"));
//	}

	 @Bean
	    public UserProvider getUserProvider() {
	        return new UserProvider() {
	            @Override
	            public FeatureUser getCurrentUser() {
	                return new SimpleFeatureUser("admin", true);
	            }
	        };
	    }

}