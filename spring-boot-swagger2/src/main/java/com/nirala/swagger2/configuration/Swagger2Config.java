package com.nirala.swagger2.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
				.globalOperationParameters(operationParameters)
//				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
//				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors
		                .basePackage("com.nirala.swagger2.controller"))
		            .paths(PathSelectors.regex("/.*"))
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	public static final Contact DEFAULT_CONTACT = new Contact("Rajani Kant Nirala", "http://www.google.com",
			"rajanikant.asr@gmail.com");

	@SuppressWarnings("rawtypes")
	private ApiInfo getApiInfo() {
		return new ApiInfo("REST Api Documentation", "REST Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT,
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
	}

//	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
//			Arrays.asList("application/json", "application/xml"));

	List<Parameter> operationParameters = Arrays
			.asList(new ParameterBuilder().name("header").description("Authorization *****************************")
					.modelRef(new ModelRef("string")).parameterType("Header").required(false).build());
}
