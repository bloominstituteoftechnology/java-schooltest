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
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)//for unit testing
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired CourseServiceImpl courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);//sub testing framework to J testing
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findCourseById() {
        //unit test
        //mock the service
        //assert the # of items returned
        //need a list



        assertEquals("JavaScript", courseService.findCourseById(2).getCoursename());

    }

    @Test
    public void deleteFound() {
        courseService.delete(2);
        assertEquals(5, courseService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNotFound() {
        courseService.delete(7);
        assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void save() {

        Course c = new Course("DustinCourse");
        c.setCourseid(8);
        Instructor newInstruct = new Instructor("Dustin");
        newInstruct.setInstructid(10);
        c.setInstructor(newInstruct);

        Course addCourse = courseService.save(c);

        assertNotNull(addCourse);

        Course foundRestaurant = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundRestaurant.getCoursename());
    }
}