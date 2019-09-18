package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    //post student
    @Test
    public void givenPostACourse() throws Exception {
        ArrayList<Course> theseClasses = new ArrayList<>();
        String courseName = "intro to Bone Marrow Extraction";
        Course newCourse = new Course(courseName);
        theseClasses.add(newCourse);

        ObjectMapper mapper = new ObjectMapper();
        String stringifiedTheseClasses = mapper.writeValueAsString(theseClasses);

        given().contentType("application/json").body(stringifiedTheseClasses).when().post("/courses/course/add").then().statusCode(200).and().body(containsString("intro to Bone Marrow Extraction"));
    }

}
