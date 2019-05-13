package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<Student, Long>
{
    ArrayList<Student> findStudentsByStudnameEquals(String name);
}
