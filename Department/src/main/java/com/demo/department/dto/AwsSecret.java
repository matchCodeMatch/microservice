package com.demo.department.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AwsSecret {
    @JsonProperty("username")
    private String userName;
    private String password;
    private String host;
    private String engine;
    private String port;
    private String dbname;
    private String dbInstanceIdentifier;
}
