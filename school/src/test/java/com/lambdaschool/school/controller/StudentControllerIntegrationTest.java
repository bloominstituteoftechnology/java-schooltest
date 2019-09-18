package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Student;
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
import static org.hamcrest.Matchers.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    //Get all students
    @Test
    public void whenMeasueredResponseTime(){
        given().when().get("/students/students").then().time(lessThan(5000L));
    }

    //post student
    @Test
    public void givenPostAStudent() throws Exception {
        ArrayList<Student> theseKids = new ArrayList<>();
        String studentName = "Edward Jones";
        Student newKid = new Student(studentName);
        theseKids.add(newKid);

        ObjectMapper mapper = new ObjectMapper();
        String stringifiedTheseKids = mapper.writeValueAsString(theseKids);

        given().contentType("application/json").body(stringifiedTheseKids).when().post("/students/student").then().statusCode(200).and().body(containsString("Edward Jones"));
    }

    //get student by id
    @Test
    public void givenStudentFoundById() throws Exception{
        long studentId = 3L;

        given().when().get("/Student/"+studentId).then().statusCode(200).and().body(containsString("Mary"));
    }
    //get by name-like
    @Test
    public void givenStudentByNameLike() throws Exception {
        String nameTest = "John";

        given().when().get("/student/namelike/" + nameTest).then().statusCode(200).and().body(containsString("John"));
    }
    //put by id
    @Test
    public void givenPutAStudentById() throws Exception {

        ArrayList<Student> theseKids = new ArrayList<>();
        Student thisKid = new Student("jack notJill");
        thisKid.setStudid(42);

        ObjectMapper mapper = new ObjectMapper();
        String stringifiedTheseKids = mapper.writeValueAsString(theseKids);

        given().contentType("application/json").body(stringifiedTheseKids).when().put("/students/student/42").then().statusCode(200).and().body(containsString("jack notJill"));
    }

    //delete student
    @Test
    public void givenDeleteAStudent()
    {
        long studentId = 10L;
        given().when().delete("/students/student/" + studentId).then().statusCode(200);
    }
}
