package com.dcb.department.client;

import com.dcb.department.entity.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("/employee/departments/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId);

    }
