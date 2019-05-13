package com.lambdaschool.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SchoolApplicationTests.class)
public class SchoolApplicationTests //may need to change this name or add a context
{

    @Test
    public void contextLoads()
    {
    }

}
