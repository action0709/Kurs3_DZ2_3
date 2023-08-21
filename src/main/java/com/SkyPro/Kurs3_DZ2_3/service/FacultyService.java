package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.FacultyNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();

    }
    public Collection<Faculty>getByColor(String color){

        return facultyRepository.findAllByColor(color);
    }
    public Collection<Faculty>getByColorOrName(String color,String name){

        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Faculty create(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public Faculty update(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyRepository.findById(id)
                .orElseThrow (FacultyNotFoundException::new);
        if(faculty.getColor()!=null){
        existingFaculty.setColor((faculty.getColor()));
        }
        if(faculty.getName()!=null){
        existingFaculty.setName((faculty.getName()));
        }
        return facultyRepository.save(existingFaculty);
    }

    public Faculty remove ( Long id){
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Faculty getByStudent(Long studentId){
        return facultyRepository.findByStudent_Id(studentId)
                .orElseThrow(FacultyNotFoundException::new);
    }
}

