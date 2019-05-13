package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)//for unit testing
@WebMvcTest(value = CourseController.class, secure = false)


public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

    @Before
    public void setUp()//creating our own test data in this test
    {




        courseList = new ArrayList<>();

        Instructor in1 = new Instructor("Sally");
        in1.setInstructid(1);
        Instructor in2 = new Instructor("Lucy");
        in2.setInstructid(2);
        Instructor in3 = new Instructor("Charlie");
        in3.setInstructid(3);

        Course c1 = new Course("Data Science");
        c1.setCourseid(1);
        c1.setInstructor(in1);
        Course c2 = new Course("JavaScript");
        c2.setCourseid(1);
        c2.setInstructor(in1);
        Course c3 = new Course("Node.js");
        c3.setCourseid(3);
        c3.setInstructor(in1);
        Course c4 = new Course("Java Back End");
        c4.setCourseid(4);
        c4.setInstructor(in2);
        Course c5 = new Course("Mobile IOS");
        c5.setCourseid(5);
        c5.setInstructor(in2);
        Course c6 = new Course("Mobile Android");
        c6.setCourseid(6);
        c6.setInstructor(in3);


        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        courseList.add(c6);

    }


    @Test
    public void listAllCourses() throws Exception{
        String apiUrl = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);//forcing data to come back

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }
}