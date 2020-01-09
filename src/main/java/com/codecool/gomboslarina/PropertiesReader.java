package com.codecool.gomboslarina;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    private String nodeUrl;
    private String baseUrl;
    private String driverPath;
    private String username;
    private String password;
    private String email;
    private String incorrectUsername;
    private String incorrectPassword;
    private String componentsUrl;
    private String glassComponentsUrl;
    private String componentsName;

    private Properties properties;
    private FileInputStream propFile;

    PropertiesReader() {
        try {
            properties = new Properties();
            propFile = new FileInputStream("application.properties");
            properties.load(propFile);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        nodeUrl = properties.getProperty("nodeUrl");
        baseUrl = properties.getProperty("baseUrl");
        driverPath = properties.getProperty("driverPath");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        email = properties.getProperty("email");
        incorrectUsername = properties.getProperty("incorrectUsername");
        incorrectPassword = properties.getProperty("incorrectPassword");
        componentsUrl= properties.getProperty("componentsUrl");
        glassComponentsUrl= properties.getProperty("glassComponentsUrl");
        componentsName = properties.getProperty("componentsName");
    }

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
}
