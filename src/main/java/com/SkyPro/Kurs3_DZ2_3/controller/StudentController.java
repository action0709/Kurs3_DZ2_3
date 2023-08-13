package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection <Student> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById (@PathVariable("id") Long id){
        return studentService.getById(id);
    }
    @GetMapping("/filtered")
    public Collection<Student> getByAge (@RequestParam("age") int age){
        return studentService.getByAge(age);
    }
    @PostMapping
    public  Student create(@RequestBody Student student){
        return studentService.create(student);
    }
    @PutMapping("/{id}")
    public Student update (@PathVariable("id") Long id,@RequestBody Student student){
        return studentService.update(id, student);
    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Long id){

        studentService.delete(id);
    }
}
