package com.minimalism.shared.service;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.minimalism.shared.domain.ServiceConfiguration;
import com.minimalism.shared.exceptions.NoSuchPathException;

public class ClientConfigHelper {
    private static final String SERVICE_OPERATING_MODE = "service.operating.mode";
    private static final String THREADS_LOADING_FACTOR = "threads.loading.factor";
    private static final String FILE_READ_BUFFER_SIZE = "file.read.buffer.size";
    private static final String SERVICE_OUTPUT_ENDPOINT = "service.output.endpoint";
    private static final String SERVICE_OUTPUT_FAILOVER_ENDPOINT = "service.output.failover.endpoint";
    private Properties serviceProperties = new Properties();

    public ClientConfigHelper(String clientName) throws IOException, NoSuchPathException, URISyntaxException {
        //var serviceRoot = AppConfigHelper.getInstance().getServiceRootDirectory();
        
        // Path clientRootPath = serviceRoot.resolve(clientName);
        // if(!Files.exists(clientRootPath, LinkOption.NOFOLLOW_LINKS)) {
        //     throw new NoSuchPathException(String.format("The directory structure for client: %s does not exist. Plese use 'setup' feature to establish the environment for the client.", clientName));
        // }
        try(var reader = new FileReader(
            FileSystemConfigHelper.getInstance()
            .getServiceClientRootDirectory(clientName).toString()
            .concat("\\service.properties"))) {
                serviceProperties.load(reader);
        }
    }

    public ServiceConfiguration getServiceConfiguration(String forClient) {
        ServiceConfiguration returnValue = new ServiceConfiguration(forClient);
        returnValue.setOperatingMode(serviceProperties.getProperty(SERVICE_OPERATING_MODE));
        returnValue.setThreadsLoadingFactor(serviceProperties.getProperty(THREADS_LOADING_FACTOR));
        returnValue.setFileReadBufferSize(serviceProperties.getProperty(FILE_READ_BUFFER_SIZE));
        returnValue.setServiceOutputEndpoint(serviceProperties.getProperty(SERVICE_OUTPUT_ENDPOINT));
        returnValue.setServiceOutputFailoverEndpoint(serviceProperties.getProperty(SERVICE_OUTPUT_FAILOVER_ENDPOINT));
        
        return returnValue;
    }
}
