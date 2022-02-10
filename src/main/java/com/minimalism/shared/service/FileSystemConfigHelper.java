package com.minimalism.shared.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Properties;

import com.minimalism.shared.exceptions.NoSuchPathException;

public class FileSystemConfigHelper {
    private static String NO_SUCH_PATH_UNDER_ROOT_TEMPLATE = "The file system with root at %s, does not have a directory for %s.";
    private static String NO_SUCH_DIRECTORY_TEMPLATE = "The file system does not have a directory for %s.";
    
    private static String SERVICE_CLIENTS_DIRECTORY="service.clients.directory";
    private static String SERVICE_INPUT_DIRECTORY = "service.input.directory";
    private static String SERVICE_OUTPUT_DIRECTORY = "service.output.directory";
    private static String SERVICE_INPUT_DATA_CSV_DIRECTORY = "service.input.data.csv.directory";
    private static String SERVICE_INPUT_DATA_BIN_DIRECTORY = "service.input.data.bin.directory";
    private static String SERVICE_OUTPUT_DATA_CSV_DIRECTORY = "service.output.data.csv.directory";
    private static String SERVICE_OUTPUT_DATA_BIN_DIRECTORY = "service.output.data.bin.directory";
    private static String SERVICE_INPUT_DATA_DEFINITION_DIRECTORY = "service.input.data.definition.directory";
    private static String SERVICE_OUTPUT_DATA_DEFINITION_DIRECTORY = "service.output.data.definition.directory";
    private static String SERVICE_INSTRUMENTATION_DIRECTORY = "service.instrumentation.directory";
    private static String SERVICE_ARCHIVE_DIRECTORY = "service.archive.directory";
    private static String SERVICE_ARCHIVE_INPUT_DATA_DIRECTORY = "service.archive.input.data.directory";
    private static String SERVICE_ARCHIVE_OUTPUT_DATA_DIRECTORY = "service.archive.output.data.directory";
    private static String SERVICE_ARCHIVE_INPUT_DATA_CSV_DIRECTORY = "service.archive.input.data.csv.directory";
    private static String SERVICE_ARCHIVE_INPUT_DATA_BIN_DIRECTORY = "service.archive.input.data.bin.directory";
    private static String SERVICE_ARCHIVE_OUTPUT_DATA_CSV_DIRECTORY = "service.archive.output.data.csv.directory";
    private static String SERVICE_ARCHIVE_OUTPUT_DATA_BIN_DIRECTORY = "service.archive.output.data.bin.directory";
    private static String SERVICE_LOST_AND_FOUND_DIRECTORY = "service.lost.and.found.directory";

    private static FileSystemConfigHelper instance;
    private static Properties fileSystemConfigProperties = new Properties();
    
    private FileSystemConfigHelper() throws IOException {
        fileSystemConfigProperties.load(getClass().getClassLoader().getResourceAsStream("filesystem.properties"));
    }
    /** 
     * @return FileSystemConfigHelper
     * @throws IOException
     */
    public static synchronized FileSystemConfigHelper getInstance() throws IOException {
        if(instance == null) {
            instance = new FileSystemConfigHelper();
        }
        return instance;
    }

    public String getServiceClientsDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_CLIENTS_DIRECTORY);
    }

    public Path getServiceClientsDirectory() throws IOException, NoSuchPathException {
        Path serviceRootPath = AppConfigHelper.getInstance().getServiceRootDirectory();
        Path clients = serviceRootPath
        .resolve(fileSystemConfigProperties.getProperty(SERVICE_CLIENTS_DIRECTORY));
        if(!Files.exists(clients, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_PATH_UNDER_ROOT_TEMPLATE, serviceRootPath.toString(), "clients"));
        }
        return clients;
    }

    /** 
     * @param clientName
     * @return Path
     * @throws IOException
     * @throws NoSuchPathException
     */
    public Path getServiceClientRootDirectory(String clientName) throws IOException, NoSuchPathException {
        Path clientRoot = getServiceClientsDirectory().resolve(clientName);
        if(!Files.exists(clientRoot, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_PATH_UNDER_ROOT_TEMPLATE, clientRoot.toString(), clientName));
        }
        return clientRoot;
    }
    
    /** 
     * @return String
     */
    public String getServiceInputDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_INPUT_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceInputDirectory(String clientName) throws NoSuchPathException, IOException {
        Path serviceRootPath = AppConfigHelper.getInstance().getServiceRootDirectory();
        Path clientRoot = getServiceClientsDirectory().resolve(clientName);
        if(!Files.exists(clientRoot, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_PATH_UNDER_ROOT_TEMPLATE, serviceRootPath.toString(), clientName));
        }
        Path inputDirectory = clientRoot.resolve(getServiceInputDirectoryName());
        if(!Files.exists(inputDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, inputDirectory));
        }
        return inputDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceOutputDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_OUTPUT_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceOutputDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path outputDirectory = getServiceClientRootDirectory(clientName).resolve(getServiceOutputDirectoryName());
        if(!Files.exists(outputDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, outputDirectory));
        }
        return outputDirectory;
    }    
    
    /** 
     * @return String
     */
    public String getServiceInputDataCSVDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_INPUT_DATA_CSV_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceInputDataCSVDirectory(String clientName) throws NoSuchPathException, IOException {
       
        Path inputDataCSVDirectory = getServiceInputDirectory(clientName).resolve(getServiceInputDataCSVDirectoryName());
        if(!Files.exists(inputDataCSVDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, inputDataCSVDirectory));
        }
        return inputDataCSVDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceInputDataBinDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_INPUT_DATA_BIN_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceInputDataBinDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path inputDataBinDirectory = getServiceInputDirectory(clientName).resolve(getServiceInputDataBinDirectoryName());
        if(!Files.exists(inputDataBinDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, inputDataBinDirectory));
        }
        return inputDataBinDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceOutputDataCSVDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_OUTPUT_DATA_CSV_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceOutputDataCSVDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path outputDataCSVDirectory = getServiceOutputDirectory(clientName).resolve(getServiceOutputDataCSVDirectoryName());
        if(!Files.exists(outputDataCSVDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, outputDataCSVDirectory));
        }
        return outputDataCSVDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceOutputDataBinDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_OUTPUT_DATA_BIN_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceOutputDataBinDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path outputDataBinDirectory = getServiceOutputDirectory(clientName).resolve(getServiceOutputDataBinDirectoryName());
        if(!Files.exists(outputDataBinDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, outputDataBinDirectory));
        }
        return outputDataBinDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceInputDataDefinitionDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_INPUT_DATA_DEFINITION_DIRECTORY);
    }    
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceInputDataDefinitionDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path inputDataDefinitionDirectory = getServiceInputDirectory(clientName).resolve(getServiceInputDataDefinitionDirectoryName());
        if(!Files.exists(inputDataDefinitionDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, inputDataDefinitionDirectory));
        }
        return inputDataDefinitionDirectory;
    }
    
    /** 
    * @return String
     */
    public String getServiceOutputDataDefinitionDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_OUTPUT_DATA_DEFINITION_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceOutputDataDefinitionDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path outputDataDefinitionDirectory = getServiceOutputDirectory(clientName)
        .resolve(getServiceOutputDataDefinitionDirectoryName());
        if(!Files.exists(outputDataDefinitionDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, outputDataDefinitionDirectory));
        }
        return outputDataDefinitionDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceInstrumentationDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_INSTRUMENTATION_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceInstrumentationDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path instrumentationDirectory = getServiceClientRootDirectory(clientName)
        .resolve(getServiceInstrumentationDirectoryName());
        if(!Files.exists(instrumentationDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, instrumentationDirectory));
        }
        return instrumentationDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceArchiveDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveDirectory(String clientName) throws NoSuchPathException, IOException {

        Path archiveDirectory = getServiceClientRootDirectory(clientName).resolve(getServiceArchiveDirectoryName());
        if(!Files.exists(archiveDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveDirectory));
        }
        return archiveDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceArchiveInputDataDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_INPUT_DATA_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveInputDataDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveInputDataDirectory = getServiceArchiveDirectory(clientName).resolve(getServiceArchiveInputDataDirectoryName());
        if(!Files.exists(archiveInputDataDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveInputDataDirectory));
        }
        return archiveInputDataDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceArchiveOutputDataDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_OUTPUT_DATA_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveOutputDataDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveOutputDataDirectory = getServiceArchiveDirectory(clientName)
        .resolve(getServiceArchiveOutputDataDirectoryName());
        if(!Files.exists(archiveOutputDataDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveOutputDataDirectory));
        }
        return archiveOutputDataDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceArchiveInputDataCSVDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_INPUT_DATA_CSV_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveInputDataCSVDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveInputDataCSVDirectory = getServiceArchiveInputDataDirectory(clientName)
                                            .resolve(getServiceArchiveInputDataCSVDirectoryName());
        if(!Files.exists(archiveInputDataCSVDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveInputDataCSVDirectory));
        }
        return archiveInputDataCSVDirectory;
    }
    
    /** 
     * @return String
     */
    public String getServiceArchiveInputDataBinDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_INPUT_DATA_BIN_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveInputDataBinDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveInputDataBinDirectory = getServiceArchiveInputDataDirectory(clientName)
                                        .resolve(getServiceArchiveInputDataBinDirectoryName());
        if(!Files.exists(archiveInputDataBinDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveInputDataBinDirectory));
        }
        return archiveInputDataBinDirectory;
    }  
    
    /** 
     * @return String
     */
    public String getServiceArchiveOutputDataCSVDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_OUTPUT_DATA_CSV_DIRECTORY);
    }    
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveOutputDataCSVDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveOutputDataCSVDirectory = getServiceArchiveOutputDataDirectory(clientName)
                                            .resolve(getServiceArchiveOutputDataCSVDirectoryName());
        if(!Files.exists(archiveOutputDataCSVDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveOutputDataCSVDirectory));
        }
        return archiveOutputDataCSVDirectory;
    }    
    
    /** 
     * @return String
     */
    public String getServiceArchiveOutputDataBinDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_ARCHIVE_OUTPUT_DATA_BIN_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceArchiveOutputDataBinDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path archiveOutputDataBinDirectory = getServiceArchiveOutputDataDirectory(clientName)
                                            .resolve(getServiceArchiveOutputDataBinDirectoryName());
        if(!Files.exists(archiveOutputDataBinDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, archiveOutputDataBinDirectory));
        }
        return archiveOutputDataBinDirectory;
    }    
    
    /** 
     * @return String
     */
    public String getServiceLostAndFoundDirectoryName() {
        return fileSystemConfigProperties.getProperty(SERVICE_LOST_AND_FOUND_DIRECTORY);
    }
    
    /** 
     * @param clientName
     * @return Path
     * @throws NoSuchPathException
     * @throws IOException
     */
    public Path getServiceLostAndFoundDirectory(String clientName) throws NoSuchPathException, IOException {
        
        Path lostAndFoundDirectory = getServiceClientRootDirectory(clientName)
                                    .resolve(getServiceLostAndFoundDirectoryName());
        if(!Files.exists(lostAndFoundDirectory, LinkOption.NOFOLLOW_LINKS)) {
            throw new NoSuchPathException(String.format(NO_SUCH_DIRECTORY_TEMPLATE, lostAndFoundDirectory));
        }
        return lostAndFoundDirectory;
    }
}
