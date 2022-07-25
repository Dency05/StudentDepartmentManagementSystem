package com.example.studentdepartmentmanagement.service;
import com.example.studentdepartmentmanagement.decorator.Department;
import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DepartmentModel> getAllDepartment() {
        return departmentRepository.findAllBySoftDeleteFalse();
    }

    @Override
    public Department getDepartment(String id) {
        Optional<DepartmentModel> departmentModel= departmentRepository.findByIdAndSoftDeleteIsFalse(id);
        DepartmentModel DepartmentModel1 = departmentModel.get();
        departmentRepository.save(DepartmentModel1);
        return modelMapper.map(DepartmentModel1, Department.class);
    }

    @Override
    public void deleteDepartment(String id) {
        Optional<DepartmentModel>  departmentModel = departmentRepository.findByIdAndSoftDeleteIsFalse(id);
        if (departmentModel.isPresent()){
           DepartmentModel departmentModel1= departmentModel.get();
            departmentModel1.setSoftDelete(true);
            departmentRepository.save(departmentModel1);
        }
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }



    @Override
    public DepartmentModel addOrUpdateDepartment(Department department, String id) throws Exception {
        if (id != null) {
            Optional<DepartmentModel> departmentModel = departmentRepository.findByIdAndSoftDeleteIsFalse(id);
            if (departmentModel.isPresent()) {
                DepartmentModel departmentModel1 = departmentModel.get();
                departmentModel1.setDepartmentName(department.getDepartmentName());
                departmentModel1.setDescription(department.getDescription());
                departmentRepository.save(departmentModel1);

                return modelMapper.map(departmentModel1, DepartmentModel.class);
            } else {
                throw new Exception("Department not found");
            }
        } else {

            DepartmentModel departmentModel = modelMapper.map(department, DepartmentModel.class);
            return departmentRepository.save(departmentModel);
        }
    }
    }


