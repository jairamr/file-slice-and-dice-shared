package com.minimalism.shared.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.minimalism.shared.exceptions.NoSuchPathException;

public class Setup {
    
    /** 
     * @param clientName
     * @throws IOException
     * @throws NoSuchPathException
     * @throws URISyntaxException
     */
    public void register(String clientName) throws IOException {
        //get rid of <sp>s in client name
        if(clientName.contains(" ")) {
            clientName = clientName.replace(" ", "_");
        }
        
        InputOutputFileSystem.createFileSystem(clientName);
    }

}
