//package com.demo.employee.configuration;
//
//import java.util.Collections;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
//import com.demo.employee.dto.AwsSecret;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import com.amazonaws.services.secretsmanager.AWSSecretsManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class EmployeeConfiguration {
//    private ObjectMapper objectMapper = new ObjectMapper();
//    @Value("${cloud.aws.credentials.access_key}")
//    private String accessKey;
//    @Value("${cloud.aws.credentials.secret_key}")
//    private String secretKey;
//    @Value("${cloud.aws.credentials.secret_name}")
//    private String secretName;
//    @Value("${cloud.aws.credentials.region}")
//    private String regionName;
//
//
////    @Bean
////    public DataSource getDataSource() throws Exception {
////        AwsSecret awsSecret = getSecret();
////        String connectionUrl = "jdbc:"+awsSecret.getEngine()+"://"+awsSecret.getHost()+":"+awsSecret.getPort()+"/employeeDb?user=admin";
////        return DataSourceBuilder
////                .create()
////                .url(connectionUrl)
////                .username(awsSecret.getUserName())
////                .password(awsSecret.getPassword())
////                .build();
////    }
//
////    @Bean
////    public AwsSecret getSecret() throws Exception {
////        AWSSecretsManager awsSecretsManager =
////                AWSSecretsManagerClientBuilder
////                        .standard()
////                        .withRegion(regionName)
////                        .withCredentials(new AWSStaticCredentialsProvider(
////                                new BasicAWSCredentials(accessKey,secretKey)
////                                )
////                        )
////                        .build();
////        return objectMapper.readValue(
////                awsSecretsManager
////                        .getSecretValue(new GetSecretValueRequest()
////                                .withSecretId(secretName))
////                        .getSecretString(),
////                AwsSecret.class
////        );
////    }
////    @Bean
////    public Docket swaggerConfig() {
////
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .paths(PathSelectors.ant("/employee/**"))
////                .build()
////                .apiInfo(new ApiInfo("EMPLOYEE API", "Employee management api description",
////                        "1.0", "http://matchCodeMatch/employee",
////                        new Contact("name", "url", "info@matchCodeMatch.com"),
////                        "opensource", "http://matchCodeMatch.com/license",
////                        Collections.emptyList()));
////    }
//}