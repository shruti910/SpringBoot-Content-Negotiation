package com.example.org.content;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements  WebMvcConfigurer {

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    //set path extension to true
    configurer.favorPathExtension(false).        //deprecated
    //set favor parameter to false
    favorParameter(true).
    parameterName("mediaType").
    //ignore the accept headers
    ignoreAcceptHeader(true).
    //dont use Java Activation Framework since we are manually specifying the mediatypes required below
    useJaf(false).                                  //deprecated
    defaultContentType(MediaType.APPLICATION_JSON).
    mediaType("xml", MediaType.APPLICATION_XML).
    mediaType("json", MediaType.APPLICATION_JSON);
  }
}