package com.ms.department.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.department.service.entity.Department;
import com.ms.department.service.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    private DepartmentController departmentController;
    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        departmentController = new DepartmentController();
        objectMapper = new ObjectMapper();
        departmentService = Mockito.mock(DepartmentService.class);

        ReflectionTestUtils.setField(departmentController, "departmentService", departmentService);
    }

    @Test
    void saveDepartment() throws Exception {
        Department department = new Department();
        mockMvc.perform(post("/department/saveDepartment")
                .content(objectMapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getDepartmentById() {
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void deleteDepartmentById() {
    }
}