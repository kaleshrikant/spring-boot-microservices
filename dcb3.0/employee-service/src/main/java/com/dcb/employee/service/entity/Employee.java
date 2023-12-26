package com.dcb.employee.service.entity;

public record Employee(Long employeeId, Long departmentId, String employeeName, int employeeAge, String employeePosition) {
}
