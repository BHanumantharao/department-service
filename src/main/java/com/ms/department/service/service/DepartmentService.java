package com.ms.department.service.service;

import com.ms.department.service.entity.Department;
import com.ms.department.service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    public DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside save department - Department service");
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {
        log.info("Inside get department by Id - Department Service");
        return departmentRepository.findByDepartmentId(departmentId);
    }

    public Department updateDepartment(Department department) {
        log.info("Inside update department - Department service");
        return departmentRepository.saveAndFlush(department);
    }

    public String deleteDepartmentById(Long departmentId) {
        log.info("Inside delete department by Id - Department Service");
        departmentRepository.deleteById(departmentId);
        return "Successfully deleted";
    }
}
