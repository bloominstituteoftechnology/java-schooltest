package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private List<Course> courseList;

    @Before
    public void setUp() throws Exception {
        courseList = new ArrayList<>();
        Course c1 = new Course();
        c1.setCoursename("Course 1");
        c1.setInstructor(new Instructor("Jim Bob"));
        courseList.add(c1);

        Course c2 = new Course();
        c2.setCoursename("Course 2");
        c2.setInstructor(new Instructor("Joe Jimmy"));
        courseList.add(c2);

        Course c3 = new Course();
        c3.setCoursename("Course 3");
        c3.setInstructor(new Instructor("Jimmothy Bobertson"));
        courseList.add(c3);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllCourses() throws Exception {
        String apiUrl = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn((ArrayList<Course>) courseList);

        RequestBuilder builder = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andReturn();
        String response = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(courseList);

        assertEquals(expected, response);
    }

}