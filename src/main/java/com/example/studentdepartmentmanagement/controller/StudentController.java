package com.example.studentdepartmentmanagement.controller;
import com.example.studentdepartmentmanagement.decorator.Student;
import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.model.DepartmentType;
import com.example.studentdepartmentmanagement.model.StudentModel;
import com.example.studentdepartmentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/add")
    public StudentModel addorupdateStudent(@RequestBody Student student, @RequestParam (required = false)
            DepartmentType departmentType, @RequestParam(required = false) String id)throws Exception {
        return studentService.addorupdateStudent(student,departmentType,id);
    }


    @GetMapping("/getAll")
    public List<StudentModel>getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping ("/get/{id}")
    public Student getStudent(@PathVariable String id)  {
        return studentService.getStudent(id);
    }
    @GetMapping ("/delete/{id}")
    public void deleteStudent( @PathVariable String id){
     studentService.deleteStudent(id);
    }
    @GetMapping("/deleteAll")
    public  String deleteAll(){
        studentService.deleteAll();
        return "deleted..";
    }
    @GetMapping("/find")
    public Page<StudentModel> conceptpagination (Pageable pageable){
        return studentService.getAllExpensePagination(pageable);
    }
}
