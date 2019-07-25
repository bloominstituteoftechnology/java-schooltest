package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{
    @Autowired
    CourseService courseService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AfindCourseById()
    {
        assertEquals("JavaScript", courseService.findCourseById(2).getCoursename());
    }

    @Test
    public void BdeleteFound()
    {
        courseService.delete(1);
        assertEquals(5, courseService.findAll().toArray().length);

    }

    @Test(expected=EntityNotFoundException.class)
    public void CdeleteNotFound()
    {

        courseService.delete(12345);

        assertEquals(5, courseService.findAll().toArray().length);
    }
}


