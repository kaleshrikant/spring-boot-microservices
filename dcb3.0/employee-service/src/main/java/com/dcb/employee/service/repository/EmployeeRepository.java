package com.dcb.employee.service.repository;

import com.dcb.employee.service.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<Employee>();

    public Employee addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    public Employee findByEmployeeId(Long employeeId) {
        return employeeList
                .stream()
                .filter(employee -> employee.employeeId().equals(employeeId))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> findAll() {
        return employeeList;
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeList
                .stream()
                .filter(employee -> employee.departmentId().equals(departmentId))
                .collect(Collectors.toList());
    }
}
