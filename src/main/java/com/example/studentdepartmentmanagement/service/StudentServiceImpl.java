package com.example.studentdepartmentmanagement.service;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.example.studentdepartmentmanagement.decorator.Student;
import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.model.DepartmentType;
import com.example.studentdepartmentmanagement.model.StudentModel;
import com.example.studentdepartmentmanagement.repository.DepartmentRepository;
import com.example.studentdepartmentmanagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<StudentModel> getAllStudent() {
        return studentRepository.findAllBySoftDeleteFalse();
    }
    @Override
    public Student getStudent(String id)  {
        Optional<StudentModel> studentModel = studentRepository.findByIdAndSoftDeleteIsFalse(id);
        StudentModel studentModel1 = studentModel.get();
        studentRepository.save(studentModel1);
        return modelMapper.map(studentModel1,Student.class);
    }

    @Override
    public void deleteStudent(String id) {
        Optional<StudentModel> studentModel = studentRepository.findByIdAndSoftDeleteIsFalse(id);
        if (studentModel.isPresent()){
            StudentModel student1 = studentModel.get();
            student1.setSoftDelete(true);
            studentRepository.save(student1);
        }
    }

    @Override
    public void deleteAll() {

        studentRepository.deleteAll();
    }

    @Override
    public Page<StudentModel> getAllExpensePagination(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public StudentModel addorupdateStudent(Student student, DepartmentType departmentType, String id) throws Exception {

        if(id!=null){
            Optional<StudentModel> studentModel = studentRepository.findByIdAndSoftDeleteIsFalse(id);
            if (studentModel.isPresent()){
                StudentModel studentModel1= studentModel.get();
                studentModel1.setStudentName(student.getStudentName());
                studentModel1.setAge(student.getAge());
                studentRepository.save(studentModel1);
                return modelMapper.map(studentModel1,  StudentModel.class);
            }
            else {
                throw new Exception("Department not found");
            }
        }
        else{
            DepartmentModel departmentModel = departmentRepository.findByDepartmentNameAndSoftDeleteIsFalse(departmentType.toString());
            if (departmentModel ==null){
                throw new Exception("DepartMent Not Found");
            }
            StudentModel studentModel = modelMapper.map(student,StudentModel.class);
            studentModel.setDepartmentId(departmentModel.getId());
            studentModel.setDepartmentType(departmentType);
            return  studentRepository.save(studentModel);
        }}}
















