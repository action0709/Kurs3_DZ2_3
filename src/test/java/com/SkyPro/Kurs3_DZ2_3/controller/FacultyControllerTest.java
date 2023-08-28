package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.Kurs3Dz23Application;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Kurs3Dz23Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class FacultyControllerTest {
@Autowired
    TestRestTemplate template;
    @Test
    void create(){

        ResponseEntity<Faculty> response = template.postForEntity("/faculty", new Faculty(null,
                        "filfac", "green"),
                Faculty.class);

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("filfac");
        assertThat(response.getBody().getColor()).isEqualTo("green");
    }
}
