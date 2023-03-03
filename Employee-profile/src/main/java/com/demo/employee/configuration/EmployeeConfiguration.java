package com.demo.employee.configuration;

        import java.util.Collections;

        import org.springframework.cloud.client.loadbalancer.LoadBalanced;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.client.RestTemplate;
        import springfox.documentation.builders.PathSelectors;
        import springfox.documentation.service.ApiInfo;
        import springfox.documentation.service.Contact;
        import springfox.documentation.spi.DocumentationType;
        import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class EmployeeConfiguration {
    @Bean
    public Docket swaggerConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/employee/**"))
                .build()
                .apiInfo(new ApiInfo("EMPLOYEE API", "Employee management api description",
                        "1.0", "http://matchCodeMatch/employee",
                        new Contact("name", "url", "info@matchCodeMatch.com"),
                        "opensource", "http://matchCodeMatch.com/license",
                        Collections.emptyList()));
    }
}