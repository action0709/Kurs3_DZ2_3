package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;
import com.SkyPro.Kurs3_DZ2_3.service.FacultyService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @SpyBean
    FacultyService facultyService;

    @Test
    void getById() throws Exception {
        Faculty faculty = new Faculty(1L, "filfak", "blue");
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("filfak"))
                .andExpect(jsonPath("$.color").value("blue"));
    }
    @Test
    void create ()throws Exception{
        Faculty faculty = new Faculty(1L, "filfak", "blue");
        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .content(objectMapper.writeValueAsString(faculty))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("filfak"))
                .andExpect(jsonPath("$.color").value("blue"));
    }
    @Test
    void update()throws Exception{
        Faculty faculty = new Faculty(1L, "filfak", "blue");
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders.put("/faculty/1")
                        .content(objectMapper.writeValueAsString(faculty))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("filfak"))
                .andExpect(jsonPath("$.color").value("blue"));
    }
    @Test
    void filteredByColor()throws Exception{

        when(facultyRepository.findAllByColor("blue"))
                .thenReturn(Arrays.asList(
                        new Faculty(1L,"filfak", "blue"),
                        new Faculty(2L, "fizfak", "red")
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/filtred?color=blue")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("filfak"));


    }

}

