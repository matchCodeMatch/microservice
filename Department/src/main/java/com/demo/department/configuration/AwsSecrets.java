//package com.demo.department.configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.secretsmanager.AWSSecretsManager;
//import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
//import com.demo.department.dto.AwsSecret;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class AwsSecrets {
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
//    @Bean
//    public DataSource getDataSource() throws Exception {
//        AwsSecret awsSecret = getSecret();
//        String connectionUrl = "jdbc:"+awsSecret.getEngine()+"://"+awsSecret.getHost()+":"+awsSecret.getPort()+"/employeeDb?user=admin";
//        return DataSourceBuilder
//                .create()
//                .url(connectionUrl)
//                .username(awsSecret.getUserName())
//                .password(awsSecret.getPassword())
//                .build();
//    }
//    @Bean
//    public AwsSecret getSecret() throws Exception {
//        AWSSecretsManager awsSecretsManager =
//                AWSSecretsManagerClientBuilder
//                        .standard()
//                        .withRegion(regionName)
//                        .withCredentials(new AWSStaticCredentialsProvider(
//                                        new BasicAWSCredentials(accessKey,secretKey)
//                                )
//                        )
//                        .build();
//        return objectMapper.readValue(
//                awsSecretsManager
//                        .getSecretValue(new GetSecretValueRequest()
//                                .withSecretId(secretName))
//                        .getSecretString(),
//                AwsSecret.class
//        );
//    }
//}