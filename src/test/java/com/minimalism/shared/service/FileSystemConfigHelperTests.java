package com.minimalism.shared.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Path;

import com.minimalism.shared.exceptions.NoSuchPathException;

import org.junit.jupiter.api.Test;

class FileSystemConfigHelperTests {
    @Test
    void testGetInstance() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertNotNull(iut);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveDirectory_io_exception() {
        FileSystemConfigHelper iut = mock(FileSystemConfigHelper.class);
        try {
            when(iut.getServiceArchiveDirectory("client_1")).thenThrow(new IOException("an I/O Exception occurred."));
        } catch (NoSuchPathException | IOException e) {
            e.printStackTrace();
        }
            
        assertThrows(IOException.class, () -> {
            iut.getServiceArchiveDirectory("client_1");
        }); 
    }

    @Test
    void testGetServiceArchiveDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveDirectoryName();
            assertEquals("archive", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveInputDataBinDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveInputDataBinDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\input\\bin", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveInputDataDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveInputDataBinDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveInputDataBinDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveInputDataBinDirectoryName();
            assertEquals("bin", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveInputDataCSVDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveInputDataCSVDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\input\\csv", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveInputDataCSVDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveInputDataCSVDirectory("clinet_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveInputDataCSVDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveInputDataCSVDirectoryName();
            assertEquals("csv", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveInputDataDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveInputDataDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\input", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveInputDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveInputDataDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveInputDataDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveInputDataDirectoryName();
            assertEquals("input", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutputDataBinDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveOutputDataBinDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\output\\bin", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutputDataBinDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveOutputDataBinDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveOutputDataBinDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveOutputDataBinDirectoryName();
            assertEquals("bin", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutputDataCSVDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveOutputDataCSVDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\output\\csv", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutPutDataCSVDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveOutputDataCSVDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveOutputDataCSVDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveOutputDataCSVDirectoryName();
            assertEquals("csv", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutputDataDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceArchiveOutputDataDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\archive\\output", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceArchiveOutputDataDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceArchiveDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceArchiveOutputDataDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceArchiveOutputDataDirectoryName();
            assertEquals("output", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceClientRootDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceClientRootDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceClientRootDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceClientRootDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceClientsDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceClientsDirectory();
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceClientsDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceClientsDirectoryName();
            assertEquals("clients", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataBinDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceInputDataBinDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\input\\bin", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataBinDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceInputDataBinDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceInputDataBinDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceInputDataBinDirectoryName();
            assertEquals("bin", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataCSVDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceInputDataCSVDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\input\\csv", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataCSVDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceInputDataCSVDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceInputDataCSVDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceInputDataCSVDirectoryName();
            assertEquals("csv", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataDefinitionDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceInputDataDefinitionDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\input\\definition", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDataDefinitionDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceInputDataDefinitionDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceInputDataDefinitionDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceInputDataDefinitionDirectoryName();
            assertEquals("definition", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceInputDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\input", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceInputDirectoryName();
            assertEquals("input", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInputDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceInputDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceInstrumentationDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceInstrumentationDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\instrumentation", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceInstrumentationDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceInstrumentationDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceInstrumentationDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceInstrumentationDirectoryName();
            assertEquals("instrumentation", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceLostAndFoundDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceLostAndFoundDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\lost+found", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceLostAndFouldDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceLostAndFoundDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceLostAndFoundDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceLostAndFoundDirectoryName();
            assertEquals("lost+found", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataBinDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceOutputDataBinDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\output\\bin", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataBinDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceOutputDataBinDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceOutputDataBinDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceOutputDataBinDirectoryName();
            assertEquals("bin", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataCSVDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceOutputDataCSVDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\output\\csv", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataCSVDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceOutputDataCSVDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceOutputDataCSVDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceOutputDataCSVDirectoryName();
            assertEquals("csv", result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataDefinitionDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceOutputDataDefinitionDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\output\\definition", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDataDefinitionDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceOutputDataDefinitionDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceOutputDataDefinitionDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceOutputDataDefinitionDirectoryName();
            assertEquals("definition", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDirectory() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            Path result = iut.getServiceOutputDirectory("client_1");
            assertEquals("C:\\Users\\jaira\\FileSliceAndDice\\clients\\client_1\\output", result.toString());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceOutputDirectory_no_such_path() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceOutputDirectory("client_4");
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetServiceOutputDirectoryName() {
        try {
            FileSystemConfigHelper iut = FileSystemConfigHelper.getInstance();
            String result = iut.getServiceOutputDirectoryName();
            assertEquals("output", result);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
