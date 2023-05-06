package com.ms.department.service;


import com.ms.department.service.repository.DepartmentRepository;
import com.ms.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

@Slf4j
class DepartmentServiceApplicationTests {

	private DepartmentService departmentService;
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp() throws Exception {
		departmentService = new DepartmentService();
		departmentRepository = Mockito.mock(DepartmentRepository.class);


		ReflectionTestUtils.setField(departmentService, "departmentRepository", departmentRepository);
	}

	@Test
	void contextLoads() {
	}

}
