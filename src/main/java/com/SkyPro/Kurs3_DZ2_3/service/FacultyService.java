package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.FacultyNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger (FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty getById(Long id) {
        logger.info("invoked method getById");
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Collection<Faculty> getAll() {
        logger.info("invoked method getAll");
        return facultyRepository.findAll();

    }
    public Collection<Faculty>getByColor(String color){
        logger.info("invoked method getByColor");

        return facultyRepository.findAllByColor(color);
    }
    public Collection<Faculty>getByColorOrName(String color,String name){
        logger.info("invoked method getByColorOrName");

        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Faculty create(Faculty faculty) {
        logger.info("invoked method create");
        return facultyRepository.save(faculty);
    }

    public Faculty update(Long id, Faculty faculty) {
        logger.info("invoked method update");
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
        logger.info("invoked method remove");
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Faculty getByStudent(Long studentId){
        logger.info("invoked method getByStudent");
        return facultyRepository.findByStudent_Id(studentId)
                .orElseThrow(FacultyNotFoundException::new);
    }
    public String getLongestName(){
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(FacultyNotFoundException::new);
    }

}

