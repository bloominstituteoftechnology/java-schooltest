package com.wkdrabbit.school.service;

import com.wkdrabbit.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructrepos;
}
