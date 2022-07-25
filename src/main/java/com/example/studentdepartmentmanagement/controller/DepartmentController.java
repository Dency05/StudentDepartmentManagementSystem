package com.example.studentdepartmentmanagement.controller;
import com.example.studentdepartmentmanagement.decorator.Department;
import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")

public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping({"/addorupdate"})
    public DepartmentModel addOrUpdateDepartment(@RequestBody Department department ,@RequestParam(required = false) String id ) throws Exception {
      return departmentService.addOrUpdateDepartment(department,id);
    }

    @GetMapping("/getAll")
   public List<DepartmentModel> getAllDepartment(){
        return departmentService.getAllDepartment();
   }
   @GetMapping("/department")
   public Department getDepartment(@RequestParam String id){
       return departmentService.getDepartment(id);
   }
    @GetMapping("/delete/department")
    public void deleteDepartment(@RequestParam String id){
        departmentService.deleteDepartment(id);
    }
    @GetMapping("/deleteAll")
    public String deleteAll(){
        departmentService.deleteAll();
        return "delete..";
    }


}
