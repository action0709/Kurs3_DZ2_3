package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.service.FacultyService;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAll(){
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public Faculty getById (@PathVariable("id") Long id){
        return facultyService.getById(id);
    }
    @GetMapping("/filtred")
    public Collection getByColor (@RequestParam("color") String color){
        return facultyService.getByColor(color);
    }

    @PostMapping
    public  Faculty create(@RequestBody Faculty faculty){
        return facultyService.create(faculty);
    }
    @PutMapping("/{id}")
    public Faculty update (@PathVariable("id") Long id,@RequestBody Faculty faculty){
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Long id){

        facultyService.delete(id);
    }
}