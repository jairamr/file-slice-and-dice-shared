package com.minimalism.shared.domain;

import com.minimalism.shared.common.AllEnums.DataSources;

/**
 * @author R Jairam Iyer
 * <p>
 * The <em>ServiceConfiguration</em> class defines the configuration settings for a client. It 
 * reads the <i> client.properties </i> file located in the base directory for the client. <i> Each
 * client has a dedicated directory structure, beginning with client's name </i>.
 * </p>
 * <p>
 * The following are the service attributes that are used:
 * <ul>
 *  <li> service.operating.mode = balanced/single - indicates if the input file should be processed in a single thread or multi-threaded </li> 
 *  <li> threads.loading.factor = 1 - a number indicating the loading of the CPU. The number of available cores ismultiplied by this number to arrive at the target number of threads</li>
 *  <li> file.read.buffer.size = 1024 - the preferred size (in KiBytes) of the input buffers used by the service. Each thread will use the same sized byffer. </li>
 *  <li> service.output.endpoint = kafka - the service where the outout objects should be directed to. </li>
 *  <li> service.output.failover.endpoint = file_system - in case the outputend point is not available. </li>
 * </ul>
 * </p>
 * 
 */
public class ServiceConfiguration {
    
    private String client;
    private String operatingMode;
    private int threadsLoadingFactor;
    private int fileReadBufferSize;
    private DataSources outputEndpoint;
    private DataSources outputFailoverEndpoint;

    public ServiceConfiguration(String clientName) {
        this.client = clientName;
    }

    public String getClientName() {
        return this.client;
    }

    public String getOperatingMode() {
        return this.operatingMode;
    }
    public void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }

    public int getThreadsLoadingFactor() {
        return this.threadsLoadingFactor;
    }
    public void setThreadsLoadingFactor(String loadingFactor) {
        this.threadsLoadingFactor = Integer.parseInt(loadingFactor);
    }

    public int getFileReadBufferSize() {
        return this.fileReadBufferSize;
    }
    public void setFileReadBufferSize(String bufferSize) throws NumberFormatException {
        this.fileReadBufferSize = Integer.parseInt(bufferSize);
    }

    public DataSources getOutputEndpoint() {
        return this.outputEndpoint;
    }
    public void setServiceOutputEndpoint(String endPoint) {
        try{
            this.outputEndpoint = Enum.valueOf(DataSources.class, endPoint.toUpperCase());
        } catch(IllegalArgumentException | NullPointerException e) {
            String osName = System.getProperty("os.name").toLowerCase();
            if(osName.indexOf("win") >= 0) {
                this.outputEndpoint = DataSources.NTFS;
            } else {
                this.outputEndpoint = DataSources.UNIX_FS;
            }
        }
    }

    public DataSources getOutputFailEndpoint() {
        return this.outputFailoverEndpoint;
    }
    
    public void setServiceOutputFailoverEndpoint(String failoverEndpoint) {
        this.outputFailoverEndpoint = Enum.valueOf(DataSources.class, failoverEndpoint.toUpperCase());
    }
}
