package com.openclassrooms.mddapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger API documentation.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Configures the Docket bean for Swagger API documentation.
     *
     * @return The configured Docket bean.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.openclassrooms.mddapi"))
                .paths(PathSelectors.any())
                .build();
    }
}