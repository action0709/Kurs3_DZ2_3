package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.Kurs3Dz23Application;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Kurs3Dz23Application.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class StudentControllerTest {
@Autowired
    TestRestTemplate template;
    @Autowired
    StudentRepository studentRepository;

@AfterEach
void clearDb(){
studentRepository.deleteAll();
}

    @Test
    void create(){

        String name = "ivan";
        Integer age= 25;
        ResponseEntity<Student> response = createStudent(name, age);

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(name);
        assertThat(response.getBody().getAge()).isEqualTo(age);
    }

    private ResponseEntity<Student> createStudent(String name, Integer age) {
        ResponseEntity<Student> response = template.postForEntity("/student", new Student(null,
                        name, age),
                Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        return response;
    }
    @Test
    void getById(){
        ResponseEntity<Student> response = createStudent("ivan", 25);
        Long studentId = response.getBody().getId();

        response = template.getForEntity("/student/" + studentId, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAge()).isEqualTo(25);
    }

    @Test
    void update(){
        ResponseEntity<Student> response = createStudent("ivan", 25);
        Long studentId = response.getBody().getId();

        template.put("/student/" + studentId, new Student(null, "ivan", 25));
         response = template.getForEntity("/student/" + studentId, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAge()).isEqualTo(25);
    }

    @Test
    void getAll(){
        createStudent("ivan", 25);
        createStudent("fedor", 23);

        ResponseEntity <Collection> response = template
                .getForEntity("/student", Collection.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
    }
    @Test
    void delete(){
        ResponseEntity<Student> response = createStudent("ivan", 25);
        Long studentId = response.getBody().getId();
        template.delete("/student/"+studentId);
        response = template.getForEntity("/student/" + studentId, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }




}
