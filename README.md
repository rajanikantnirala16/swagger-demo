```Swagger integration with Spring:
Before learning swagger:
	Why do we need to document your RESTful API?
Think about the consumer.
How does the consumer know?
•	What is format of the request?
•	What content types your API supports?
•	What is the structure of the response?
•	Do you use HATEOAS?
•	How to test your API?
•	What kind of security mechanism you use?
	REST does not specify a documentation standard or a contract like SOAP (WSDL). REST gives you the flexibility to choose your documentation format and approach. But that does not mean “No documentation”

	How do you document your RESTful API?
There are multiple approaches to documenting your RESTful API
•	WADL (Stand for web application descriptor language)
•	RESTDocs
•	Swagger or OpenDocs

	What Is Swagger?
o	Swagger allows you to describe the structure of your APIs so that machines can read them.
o	 While reading your API’s structure, we can automatically build beautiful and interactive API documentation. 
o	We can also automatically generate client libraries for your API in many languages and explore other possibilities like automated testing. 
o	Swagger does this by asking your API to return a YAML or JSON that contains a detailed description of your entire API. 
o	Swagger follows  OpenAPI Specification The specification asks you to include information like:
•	What are all the operations that your API supports?
•	What are your API’s parameters and what does it return?
•	Does your API need some authorization?
•	And even fun things like terms, contact information and license to use the API.
	Note: You can write a Swagger spec for your API manually

	How to integrate and generate api specification document for REST application using Swagger:
1.	Add below dependencies to your application pom:
<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
	    		<version>2.7.0</version>		
	  	</dependency>
	  		<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger-ui</artifactId>
	    			<version>2.7.0</version>		
	  		</dependency>	
Integrating Swagger 2 into the Project by writing the SwaggerConfig class:
@Configuration 
@EnableSwagger2  
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}

@Configuration  this annotation indicates to spring container that, this is a configuration class which perform a required operation.
@EnableSwagger2  This annotation enabled the swagger2 integration to your project.

Note: You can also configure this class using xml: 
<bean class="com.nirala.config.SwaggerConfig"/> 
Docket  It will initialize the api configuration mechanism for swagger specification 2.0.
select()  It will returns an instance of ApiSelectorBuilder to give full control over the endpoints exposed via swagger.
apis()  It allows selection of RequestHandler's using a predicate. Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation and basePackage.
RequestHandlerSelectors.basePackage scans for the api inside the package
paths()   allows selection of Path's using a predicate. 
This method act as an additional filter to generate documentation for the api with path “”
Build ()  The selector needs to be built after configuring the api and path selectors.

Swagger UI Endpoint Configuration:
To expose Swagger UI endpoint /swagger-ui.html and /v2/api-docs , we need to override addResourceHandlers() of WebMvcConfigurerAdapter.
So, if you are using springBoot then you need to annotate your configuration class with @EnableWebMvc annotation or extends your configuration class to WebMvcConfigurerAdapter.

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
             In XML:
<mvc:resources mapping="swagger-ui.html" location="classpath:/META- 
INF/resources/"/>
<mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/"/>

So, your final url will be like:
http://localhost:8080/spring-app/swagger-ui.html 
http://localhost:8080/spring-app/v2/api-docs  

Below is some annotation that will use to beautify your documentation:
@ApiOperation: Provides documentation for REST web service method, for example what operation this method performs. 
@ApiParam: Provides documentation about parameter of REST web service method. 
@ApiResponses: Provides documentation about response of REST web service method. 
@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

@GetMapping("{id}")
	@ApiOperation(value = "Get by Id", notes = "Pass Id and the REST web service method will return an user details.
public ResponseEntity<Article> getById(
@ApiParam(value = "The values of Id can be 1,2 etc.", required = true)	       
@PathVariable("id") Integer id) {
@ApiImplicitParams: This is the wrapper for @ApiImplicitParam annotation. 
@ApiImplicitParam: Represents a single parameter in an API operation and is used within @ApiImplicitParams to provide documentation for REST web service method parameter. @ApiParam is also used to provide documentation but it is bound to JAX-RS parameter. 
@PutMapping("user")
	@ApiOperation(value = "Update user", notes = "This REST web service method will update user.")
	@ApiImplicitParams(
	   @ApiImplicitParam(name="user", value="Pass user with all fields.") 
	)
@Api – We can add this Annotation to the controller to add basic information regarding the controller.
@Api(value = "Swagger2DemoRestController", description = "REST APIs related to Student Entity!!!!")
	@RestController
	public class Swagger2DemoRestController {
	    ...
	}
@ApiModelProperty – This annotation is used in the Model property to add some description to the Swagger output for that model attribute.
@SwaggerDefinition  This is also one another way to exposing Meta API Information Using @SwaggerDefinition as shown below. The information in the class is self-explanatory.
@SwaggerDefinition(
        info = @Info(
                description = "Awesome Resources",
                version = "V12.0.12",
                title = "Awesome Resource API",
                contact = @Contact(
                   name = "Rajani Kant Nirala", 
                   email = "rajanikantnirala@gmail.com", 
                   url = "http://www.google.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://www.google.com")
)
public interface ApiDocumentationConfig {

}








A Quick Overview of Swagger-Core Annotations
@Api	Marks a class as a Swagger resource.
@ApiModel	Provides additional information about Swagger models.
@ApiModelProperty	Adds and manipulates data of a model property.
@ApiOperation	Describes an operation or typically an HTTP method against a specific path.
@ApiParam	Adds additional meta-data for operation parameters.
@ApiResponse	Describes a possible response of an operation.
@ApiResponses	A wrapper to allow a list of multiple ApiResponse objects.

Q. What is the relationship between swagger-ui and springfox-swagger-ui?
A.	It can be a little confusing:
o	Swagger Spec is a specification.
o	Swagger Api - an implementation of that specification that supports jax-rs, restlet, jersey etc.
o	Springfox libraries in general - another implementation of the specification focused on the spring based ecosystem.
o	Swagger.js and Swagger-ui - are client libraries in javascript that can consume swagger specification.
o	springfox-swagger-ui - the one that you’re referring to, is just packaging swagger-ui in a convenient way so that spring services can serve it up.
https://springfox.github.io/springfox/docs/snapshot/#answers-to-common-questions-and-problems
```
