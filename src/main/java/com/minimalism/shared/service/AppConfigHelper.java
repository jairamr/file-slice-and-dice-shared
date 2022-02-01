package com.minimalism.shared.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfigHelper {
    private static String SERVICE_ROOT_DIRECTORY = "service.root.directory";
    
    private static AppConfigHelper instance;
    private static Properties appProperties = new Properties();
    
    private AppConfigHelper() throws IOException {
        var basePath = Paths.get(System.getProperty("user.dir")).toAbsolutePath();

        var toPropertiesFile = basePath.resolve("src/main/resources/app.properties");
        try(var reader = new FileReader(toPropertiesFile.toString())) {
            appProperties.load(reader);
        }
    }
    /** 
     * @return AppConfigHelper
     * @throws IOException
     */
    public static synchronized AppConfigHelper getInstance() throws IOException {
        if(instance == null) {
            instance = new AppConfigHelper();
        }
        return instance;
    }
    /** 
     * @return Path
     */
    public Path getServiceRootDirectory() {
        return Paths.get(appProperties.getProperty(SERVICE_ROOT_DIRECTORY));
    }
    
}
