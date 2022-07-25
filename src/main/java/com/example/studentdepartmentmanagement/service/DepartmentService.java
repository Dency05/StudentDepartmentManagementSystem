package com.example.studentdepartmentmanagement.service;

import com.example.studentdepartmentmanagement.decorator.Department;
import com.example.studentdepartmentmanagement.model.DepartmentModel;

import java.util.List;


public interface DepartmentService {
    List<DepartmentModel> getAllDepartment();
    Department getDepartment(String id);
    void deleteDepartment(String id);

    void deleteAll();
    DepartmentModel addOrUpdateDepartment(Department department, String id) throws Exception;
}
