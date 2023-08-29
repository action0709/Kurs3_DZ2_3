package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.Kurs3Dz23Application;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Kurs3Dz23Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class FacultyControllerTest {
@Autowired
    TestRestTemplate template;
    @Autowired
    FacultyRepository facultyRepository;

@AfterEach
void clearDb(){
facultyRepository.deleteAll();
}

    @Test
    void create(){

        String name = "filfac";
        String color= "green";
        ResponseEntity<Faculty> response = createFaculty(name, color);

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(name);
        assertThat(response.getBody().getColor()).isEqualTo(color);
    }

    private ResponseEntity<Faculty> createFaculty(String name, String color) {
        ResponseEntity<Faculty> response = template.postForEntity("/faculty", new Faculty(null,
                        name, color),
                Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        return response;
    }
    @Test
    void getById(){
        ResponseEntity<Faculty> response = createFaculty("filfac", "green");
        Long facultyId = response.getBody().getId();

        response = template.getForEntity("/faculty/" + facultyId, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("green");
    }

    @Test
    void update(){
        ResponseEntity<Faculty> response = createFaculty("filfac", "green");
        Long facultyId = response.getBody().getId();

        template.put("/faculty/" + facultyId, new Faculty(null, "filfac", "red"));
         response = template.getForEntity("/faculty/" + facultyId, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("red");
    }

    @Test
    void getAll(){
        createFaculty("math", "red");
        createFaculty("filfac", "blue");

        ResponseEntity <Collection> response = template
                .getForEntity("/faculty", Collection.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
    }



}
