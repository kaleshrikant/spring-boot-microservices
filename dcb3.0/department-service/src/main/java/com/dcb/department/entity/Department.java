package com.dcb.department.entity;

import java.util.ArrayList;
import java.util.List;


public class Department {
    private Long departmentId;
    private String departmentName;

    private List<Employee> employeList = new ArrayList<Employee>();

    public Department() {
    }

    public Department(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employee> employeList) {
        this.employeList = employeList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeList=" + employeList +
                '}';
    }
}
