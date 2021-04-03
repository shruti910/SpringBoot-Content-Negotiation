## SpringBoot-Content-Negotiation
### Repository to demonstrate the Content Negotiation Strategy (Using URL parameter in the request) in Spring Boot

 The URL Parameter Strategy (configured for 2 media types : **JSON and XML**)


```
@Configuration
public class WebConfig implements  WebMvcConfigurer {

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

   
    configurer.favorPathExtension(false).        //deprecated,  sets path extension strategy to true
   
    favorParameter(true).                       //enables URL parameter strategy
    parameterName("mediaType").               //parameter name to be given in URL
      
    ignoreAcceptHeader(true).                      //ignore the accept headers
   
    useJaf(false).                                  //deprecated,  don't use Java Activation Framework since we are manually specifying the mediatypes required below
    defaultContentType(MediaType.APPLICATION_JSON).     //default media type
    mediaType("xml", MediaType.APPLICATION_XML).
    mediaType("json", MediaType.APPLICATION_JSON);
  }
}

```

We can test our Endpoint by hitting the URL and giving media type as a parameter:

- localhost:9001/students?mediaType=xml
- localhost:9001/students?mediaType=json

