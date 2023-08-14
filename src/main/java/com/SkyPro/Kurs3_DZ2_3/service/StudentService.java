package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.FacultyNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.exception.StudentNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    public Collection<Student> getAll() {
        return studentRepository.findAll();

    }
    public Collection<Student>getByAge(Integer age){
        return studentRepository.findAllByAge(age);
    }


    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                        .orElseThrow (StudentNotFoundException::new);
       Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
        Optional.ofNullable(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(existingStudent);
    }

    public Student remove (long id){
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(existingStudent);
        return existingStudent;
            }
}
