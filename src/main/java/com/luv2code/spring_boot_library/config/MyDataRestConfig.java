package com.luv2code.spring_boot_library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luv2code.spring_boot_library.entity.Book;
import com.luv2code.spring_boot_library.entity.Review;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
    
    private   String theAllowedOrigins="http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                             CorsRegistry cors){
    
                                                HttpMethod[] theUnsupportedActions={
                                                    HttpMethod.POST,
                                                    HttpMethod.PATCH,
                                                    HttpMethod.DELETE,
                                                    HttpMethod.PUT};
                                                    
                                                    config.exposeIdsFor(Book.class);
                                                    config.exposeIdsFor(Review.class);

                                                    disableHttpMethods(Book.class,config,theUnsupportedActions);
                                                    disableHttpMethods(Review.class, config, theUnsupportedActions);
                                                    /*Configure CORS */
                                                    cors.addMapping(config.getBasePath()+"/**")
                                                    .allowedOrigins(theAllowedOrigins).allowedHeaders("*");

                                                
                                                
                                                }
    private void disableHttpMethods(Class theClass,
    RepositoryRestConfiguration config,
    HttpMethod[] theUnsupportedHttpMethods){


                  config.getExposureConfiguration()
                  .forDomainType(theClass)
                  .withItemExposure((metadata,httpMethods)->
                   httpMethods.disable(theUnsupportedHttpMethods))
                   .withCollectionExposure((metadata,httpMethods)->
                   httpMethods.disable(theUnsupportedHttpMethods));
    }
       
}
