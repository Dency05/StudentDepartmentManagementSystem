package com.example.studentdepartmentmanagement.repository;

import com.example.studentdepartmentmanagement.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<StudentModel,String> {
    Optional<StudentModel> findByIdAndSoftDeleteIsFalse(String id);
    List<StudentModel> findAllBySoftDeleteFalse();


}
