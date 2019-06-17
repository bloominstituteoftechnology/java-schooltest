package com.wkdrabbit.school.repository;

import com.wkdrabbit.school.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<Student, Long> {
    ArrayList<Student> findStudentsByStudnameEquals(String name);
}
