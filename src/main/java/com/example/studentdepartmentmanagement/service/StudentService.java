package com.example.studentdepartmentmanagement.service;

import com.example.studentdepartmentmanagement.decorator.Student;
import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.model.DepartmentType;
import com.example.studentdepartmentmanagement.model.StudentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface StudentService {

    List<StudentModel> getAllStudent();
   Student getStudent(String id) ;
    void deleteStudent(String id);
    void deleteAll();



    Page<StudentModel> getAllExpensePagination(Pageable pageable);

    StudentModel addorupdateStudent(Student student,DepartmentType departmentType, String id) throws Exception;
}
