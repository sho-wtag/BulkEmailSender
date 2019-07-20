package com.bracbank.BulkEmailSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
public class BulkEmailSenderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BulkEmailSenderApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BulkEmailSenderApplication.class);
    }

    @Bean
    public Docket lastApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("BulkEmailSender")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("BulkEmailSender")
                .description("BulkEmailSender web version")
                .version("2.0")
                .build();
    }

}
