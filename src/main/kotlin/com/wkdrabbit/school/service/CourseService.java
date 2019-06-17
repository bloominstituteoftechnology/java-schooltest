package com.wkdrabbit.school.service;

import com.wkdrabbit.school.model.Course;
import com.wkdrabbit.school.view.CountStudentsInCourses;

import java.util.ArrayList;

public interface CourseService {
    ArrayList<Course> findAll();

    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    void delete(long id);
}
