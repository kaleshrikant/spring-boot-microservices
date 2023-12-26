package com.dcb.department.repository;

import com.dcb.department.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departmentList = new ArrayList<Department>();

    public Department addDepartment(Department department) {
        departmentList.add(department);
        return department;
    }

    public Department findByDepartmentId(Long departmentId) {
        return departmentList
                    .stream()
                    .filter(department -> department.getDepartmentId().equals(departmentId))
                    .findFirst()
                    .orElseThrow();
    }

    public List<Department> findAll() {
        return departmentList;
    }
}
