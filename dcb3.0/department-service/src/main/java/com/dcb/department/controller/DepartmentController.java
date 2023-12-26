package com.dcb.department.controller;

import com.dcb.department.entity.Department;
import com.dcb.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DepartmentController.class));

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/")
    public Department addDepartment(@RequestBody Department department) {
        LOGGER.info("Department addDepartment() : {}");
        return departmentRepository.addDepartment(department);
    }

    @GetMapping("/")
    public List<Department> findAll() {
        LOGGER.info("Department findAll() : {}");
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findByDepartmentId(@PathVariable("id") Long departmentId) {
        LOGGER.info("Department findByDepartmentId() : {}");
        return departmentRepository.findByDepartmentId(departmentId);
    }

}
