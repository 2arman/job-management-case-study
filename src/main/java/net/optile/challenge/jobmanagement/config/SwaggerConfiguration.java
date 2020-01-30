package net.optile.challenge.jobmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()//.paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("net.optile"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfo(
                        "Job Management - the Optile Case Study",
                        "API Documentation for Job Management Services .",
                        "0.0.1",
                        null,
                        new Contact("Arman Ajabkhani", null, "arm.khani@gmail.com"),
                        null,
                        null,
                        Collections.emptyList()));
    }
}