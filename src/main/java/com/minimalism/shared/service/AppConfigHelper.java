package com.minimalism.shared.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfigHelper {
    private static String SERVICE_ROOT_DIRECTORY = "service.root.directory";
    
    private static AppConfigHelper instance;
    private static Properties appProperties = new Properties();
    
    private AppConfigHelper() throws IOException {
        appProperties.load(getClass().getClassLoader().getResourceAsStream("app.properties"));
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
