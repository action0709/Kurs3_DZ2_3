package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;
import com.SkyPro.Kurs3_DZ2_3.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @SpyBean
    StudentService studentService;

    @Test
    void getById() throws Exception {
        Student student = new Student(1L, "petr", 25);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("petr"))
                .andExpect(jsonPath("$.age").value("25"));
    }
    @Test
    void create ()throws Exception{
    Student student = new Student(1L, "petr", 25);
    when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("petr"))
                .andExpect(jsonPath("$.age").value("25"));
    }
    @Test
    void update()throws Exception{
        Student student = new Student(1L, "petr", 25);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.put("/student/1")
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("petr"))
                .andExpect(jsonPath("$.age").value("25"));
    }
    @Test
void filteredByAge()throws Exception{

        when(studentRepository.findAllByAgeBetween(10,26))
                .thenReturn(Arrays.asList(
                        new Student(1L, "petr", 25),
                        new Student(2L, "ivan", 23)
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/by-age?min=10&max=26")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("petr"))
                .andExpect(jsonPath("$[1].name").value("ivan"));

    }

    @Test
    void findByFaculty() throws Exception{
        List<Student> students = Arrays.asList(
                new Student(1L, "petr", 25),
                new Student(2L, "ivan", 23));
        Faculty faculty = new Faculty(1L, "filfac", "red");
faculty.setStudent(students);
        when(studentRepository.findAllByFaculty_Id(1L)).thenReturn(
                students);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/by-faculty?facultyId=1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("petr"));

    }

}

