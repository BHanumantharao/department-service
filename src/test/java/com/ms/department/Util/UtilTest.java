package com.ms.department.Util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ms.department.service.entity.Department;
import org.apache.commons.io.IOUtils;

public class UtilTest {

    // Convert Json file to POJO class with data
    public static Object getObject(Class c, String fileName) throws IOException {
        String payLoad = IOUtils.toString(new Object() {}.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.readValue(payLoad, c);
    }

    public static Object getObjectFromMap(String fileName) throws IOException {
        String payLoad = IOUtils.toString(new Object() {}.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.readValue(payLoad, new TypeReference<Map<String, Map<String, Department>>>() {});
    }

    public static Object getObjectFromDepartmentMap(String fileName) throws IOException {
        String payLoad = IOUtils.toString(new Object() {}.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.readValue(payLoad, new TypeReference<Map<String, List<Department>>>() {});
    }


}
