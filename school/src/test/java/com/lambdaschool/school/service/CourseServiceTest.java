package com.lambdaschool.school.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCourseById() {
        assertEquals("Data Science", courseService.findCourseById(1));
    }

    @Test
    void testDeleteFound() {
        courseService.delete(4);
    }
    @Test
    void deleteNotFound() throws EntityNotFoundException{
        courseService.delete(101);
    }
}