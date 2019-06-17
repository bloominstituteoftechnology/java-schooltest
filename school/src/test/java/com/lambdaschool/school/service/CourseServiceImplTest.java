package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findCourseById() {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test
    public void deleteFound() {
        courseService.delete(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNotFound() {
        courseService.delete(7);
    }

    @Test
    public void save() {
        Instructor instructor = new Instructor("TestInstructor");

        Course course = new Course("TestCourse", instructor);

        instructorService.save(instructor);
        Course savedCourse = courseService.save(course);

        assertEquals(course.getCoursename(), savedCourse.getCoursename());
        assertEquals(course.getInstructor().getInstructname(), savedCourse.getInstructor().getInstructname());
    }
}