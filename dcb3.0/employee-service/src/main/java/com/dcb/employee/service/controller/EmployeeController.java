package com.dcb.employee.service.controller;

import com.dcb.employee.service.entity.Employee;
import com.dcb.employee.service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(EmployeeController.class));

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        LOGGER.info("Employee addEmployee() : {}");
        return employeeRepository.addEmployee(employee);
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        LOGGER.info("Employee findAll() : {}");
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findByEmployeeId(@PathVariable("id") Long employeeId) {
        LOGGER.info("Employee findByEmployeeId() : {}");
        return employeeRepository.findByEmployeeId(employeeId);
    }

    @GetMapping("/departments/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee findByDepartmentId() : {}");
        return employeeRepository.findByDepartmentId(departmentId);
    }

}
