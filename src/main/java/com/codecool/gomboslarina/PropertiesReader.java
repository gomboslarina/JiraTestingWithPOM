package com.codecool.gomboslarina;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    //private String componentsUrl = System.getenv("COMPURL");
    //private String glassComponentsUrl = System.getenv("GLASSCOMPURL");
    //private String componentsName = System.getenv("COMPNAME");
    private static String driverPath = System.getenv("DRIVERPATH");
    private String nodeUrl = System.getenv("NODEURL");
    private String baseUrl = System.getenv("BASEURL");
    private String username = System.getenv("USERNAME");
    private String password = System.getenv("PASSWORD");
    //private String email = System.getenv("EMAIL");
    //private String incorrectPassword = System.getenv("INCORRECT_PASSWORD");
    //private String incorrectUsername = System.getenv("INCORRECT_USERNAME");

    private Properties properties;
    private FileInputStream propFile;


    public String getNodeUrl() {
        return nodeUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
/*
    public String getEmail() {
        return email;
    }

    public String getIncorrectUsername() {
        return incorrectUsername;
    }

    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    public String getComponentsUrl() {
        return componentsUrl;
    }

    public String getGlassComponentsUrl() {
        return glassComponentsUrl;
    }

    public String getComponentsName() {
        return componentsName;
    }

 */
}