package com.lambdaschool.school.service;


import com.lambdaschool.school.SchoolApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired
    CourseService courseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        assertEquals(6, courseService.findAll().size());
    }

    @Test
    public void delete() {
        courseService.delete(1);
        assertEquals(5, courseService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNotFound(){
        courseService.delete(10000);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void findCourseById() {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findCourseByIdNo(){
        assertEquals("Data Science", courseService.findCourseById(1000).getCoursename());
    }
}