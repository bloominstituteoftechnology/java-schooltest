package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)

public class StudentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private StudentService studentService;

    private List<Student> studentList;


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    public void listAllStudents() throws Exception {
        String apiUrl = "/students/students";
        Mockito.when(studentService.findAll()).thenReturn(studentList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(studentList);

        assertEquals("Rest API returns list", er, tr);
    }

    @Test
    public void getStudentById() throws Exception {
        String apiUrl = "/students/student/1";

        Mockito.when(studentService.findStudentById(1)).thenReturn(studentList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(studentList.get(1));

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getStudentByIdNotFound() throws Exception {
        String apiUrl = "/students/student/100";

        Mockito.when(studentService.findStudentById(100)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();


        String er = "";

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getStudentByNameContaining() throws Exception {
        String apiUrl = "/student/namelike/John";

        Mockito.when(studentService.findStudentByNameLike("John")).thenReturn((List<Student>) studentList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(studentList.get(0));

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void addNewStudent() throws Exception {
        String apiUrl = "/students/student";

        // build a restaurant
        ArrayList<Student> thisPay = new ArrayList<>();
        String studentName = "johnny";
        Student student1 = new Student(studentName);
        student1.setStudid(90);
        ObjectMapper mapper = new ObjectMapper();
        String kidString = mapper.writeValueAsString(student1);

        Mockito.when(studentService.save(any(Student.class))).thenReturn(student1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(kidString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateStudent() throws Exception {
        String apiUrl = "/students/student/{StudentId}";
        ArrayList<Student> theseKids = new ArrayList<>();
        Student student2 = new Student("Beavis");
        student2.setStudid(101);

        Mockito.when(studentService.update(student2, 101L)).thenReturn(student2);
        ObjectMapper mapper = new ObjectMapper();
        String kidString = mapper.writeValueAsString(student2);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 101L)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(kidString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteStudentById() throws Exception {
        String apiUrl = "/students/student/{StudentId}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "12").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}