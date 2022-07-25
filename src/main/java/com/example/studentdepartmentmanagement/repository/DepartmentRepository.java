package com.example.studentdepartmentmanagement.repository;

import com.example.studentdepartmentmanagement.model.DepartmentModel;
import com.example.studentdepartmentmanagement.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<DepartmentModel,String> {
    Optional<DepartmentModel> findByIdAndSoftDeleteIsFalse(String id);
    //boolean findByName(String type);

    List<DepartmentModel> findAllBySoftDeleteFalse();
    DepartmentModel findByDepartmentNameAndSoftDeleteIsFalse(String DepartmentName);



}
