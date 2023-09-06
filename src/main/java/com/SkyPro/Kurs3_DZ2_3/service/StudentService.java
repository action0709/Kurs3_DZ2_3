package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.FacultyNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.StudentNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }
    public Student getByName(String name) {

        return studentRepository.findByName(name);

    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();

    }

    public Collection<Student> getByAge(Integer age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }


    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
        Optional.ofNullable(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(existingStudent);
    }

    public Student remove(long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(existingStudent);
        return existingStudent;
    }

    public Collection<Student> getByFacultyId(Long facultyId) {
        return studentRepository.findAllByFaculty_Id(facultyId);
    }
    public long count(){
        return studentRepository.countStudents();
    }
    public  double average(){
        return studentRepository.averegeAge();
    }
    public List<Student> getLastStudent (int quantity){
        return studentRepository.findLastStudents(quantity);
    }
}
