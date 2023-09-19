package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @DeleteMapping("/{id}")
    public Student remove(@PathVariable("id") Long id){

        return studentService.remove(id);}
        @GetMapping("/by-age")
        public Collection<Student> getByAge (@RequestParam int min,@RequestParam int max){
            return studentService.getByAgeBetween (min, max);
        }
    @GetMapping("/by-name")
    public Student getByname (@RequestParam String name){
        return studentService.getByName(name);
    }
    @GetMapping("/by-faculty")
    public Collection<Student> getByFaculty (@RequestParam Long facultyId){
        return studentService.getByFacultyId(facultyId);
    }
    @GetMapping("/count")
    public long count(){
        return studentService.count();
    }
    @GetMapping("/average")
    public double average(){
        return studentService.average();
    }
    @GetMapping("/last-five")
    public List<Student> getLastFive()  {
        return studentService.getLastStudent(5);
    }

    @GetMapping("/thread/async")
    public void printAsync(){
        studentService.printAsync();
    }
    @GetMapping("/thread/sync")
    public void printSync(){
        studentService.printSync();
    }
    @GetMapping("/stream/starts-with-a")
    public List<String> startsWithA(){
        return studentService.getAllStartsWith();
    }
    @GetMapping("/stream/average-age")
    public double getAverageAge(){
        return studentService.getAverageAge();
    }
    }

