package com.ms.department.service.controller;

import com.ms.department.service.entity.Department;
import com.ms.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;


    @PostMapping("/saveDepartment")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("Inside save department Department Controller");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside get department by Id - Department Controller");
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/updateDepartment")
    public Department updateDepartment(@RequestBody Department department) {
        log.info("Inside update department Department Controller");
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside delete department by Id - Department Controller");
        return departmentService.deleteDepartmentById(departmentId);
    }
}
