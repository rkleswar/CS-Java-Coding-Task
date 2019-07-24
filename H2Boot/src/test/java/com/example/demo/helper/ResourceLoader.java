package com.example.demo.helper;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * General utility class dealing with project resources
 * 
 * @author KotilingeswararaoR
 */
public class ResourceLoader {

	/**
     * Reads the contents from a resource in the project and returns in string
     * format
     * 
     * @param resourceName
     * @return String resourceContent
     * @throws IOException
     */
    public static String readFile(String resourceName) throws IOException {
        return IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName),
                MockData.UTF_8.getString());
    }

    /**
     * This method used to map the object to json string
     * 
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String asJsonString(final Object object) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(object);
        return jsonContent;
    }
    
    /**
     * A generic Reads the contents from a resource in the project and returns
     * in string format and converts to a class
     * 
     * @param resourceName
     * @param encoding
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T readAndGetObject(String resourceName, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(readFile(resourceName), clazz);
    }


}

