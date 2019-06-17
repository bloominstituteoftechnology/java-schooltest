package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

    @Before
    public void setUp() {
        courseList = new ArrayList<>();

        Course course1 = new Course("TestCourse1");
        course1.setCourseid(1);
        Course course2 = new Course("TestCourse2");
        course2.setCourseid(2);
        Course course3 = new Course("TestCourse3");
        course3.setCourseid(3);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void listAllCourses() throws Exception {
        String url = "/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String responseStr = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        String expectedResult = mapper.writeValueAsString(courseList);

        assertEquals(expectedResult, responseStr);
    }


}