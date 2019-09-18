package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTests.class)

public class StudentServiceTest {
    //mocks- mock data
    //stubs-  code that forces return value
    //java calls everything mocks.

    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testFindAll() {
        assertEquals(13, studentService.findAll().size());
    }

    @org.junit.Test
    public void testFindStudentById() {
        assertEquals("John", studentService.findStudentById(1).getStudname());
    }

    @org.junit.Test
    public void testFindStudentByNameLike() {
        assertEquals("John", studentService.findStudentByNameLike("%Jo%"));
    }

    @org.junit.Test(expected = EntityNotFoundException.class)
    public void testDelete() {
       studentService.delete(4);
    }

    @org.junit.Test
    public void testSave() {
    }

    @org.junit.Test
    public void testUpdate() {
        ArrayList<Student> thisStudent = new ArrayList<>();
        Student ninja = new Student("Ninja");
        Student updatedStudent = studentService.update(ninja, 5);
        assertEquals("Ninja", updatedStudent.getStudname());
    }
}