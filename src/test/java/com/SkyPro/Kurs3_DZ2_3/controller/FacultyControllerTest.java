package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.Kurs3Dz23Application;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(classes = Kurs3Dz23Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class FacultyControllerTest {

    TestRestTemplate template;
    @Test
    void create(){

        template.postForEntity("/faculty", new Faculty(null,
                        "filfac", "green"),
                Faculty.class);
    }
}
