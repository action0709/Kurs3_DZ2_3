package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.StudentNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import com.SkyPro.Kurs3_DZ2_3.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger (StudentService.class);
    private final StudentRepository studentRepository;
    private int counter=0;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getById(Long id) {
logger.info("invoked method getById");
        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }
    public Student getByName(String name) {
        logger.info("invoked method getByName");
        return studentRepository.findByName(name);

    }

    public Collection<Student> getAll() {
        logger.info("invoked method getAll");
        return studentRepository.findAll();

    }

    public Collection<Student> getByAge(Integer age) {
        logger.info("invoked method getByAge");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        logger.info("invoked method getByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }


    public Student create(Student student) {
        logger.info("invoked method create");
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        logger.info("invoked method update");
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        Optional.ofNullable(student.getName()).ifPresent(existingStudent::setName);
        Optional.ofNullable(student.getAge()).ifPresent(existingStudent::setAge);
        return studentRepository.save(existingStudent);
    }

    public Student remove(long id) {
        logger.info("invoked method remove");
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(existingStudent);
        return existingStudent;
    }

    public Collection<Student> getByFacultyId(Long facultyId) {
        logger.info("invoked metod getByFacultyId");
        return studentRepository.findAllByFaculty_Id(facultyId);
    }
    public long count(){
        logger.info("invoked metod count");
        return studentRepository.countStudents();
    }
    public  double average(){
        logger.info("invoked metod average");
        return studentRepository.averegeAge();
    }
    public List<Student> getLastStudent (int quantity){
        logger.info("invoked metod getLastStudent");
        return studentRepository.findLastStudents(quantity);
    }
    public void printAsync(){
        List<Student> all = studentRepository.findAll();
        System.out.println(all.get(0));
        System.out.println(all.get(1));

        Thread t1 = new Thread(() -> {
            System.out.println(all.get(2));
            System.out.println(all.get(3));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(all.get(4));
            System.out.println(all.get(5));
        });
        t1.start();
        t2.start();
    }
    public void printSync(){

        List<String> all = getAll()
                .stream()
                .map(Student::getName)
                .toList();

        printSync(all);
        printSync(all);

         new Thread(() -> {
            printSync(all);
            printSync(all);
                   }).start();
         new Thread(() -> {
            printSync(all);
            printSync(all);
        }).start();

    }
    private synchronized void printSync(List<String> students){
        logger.info(students.get(counter++%students.size()));


}


public List <String>getAllStartsWith() {
    return studentRepository.findAll().stream()
            .map(Student::getName)
            .filter(s -> s.startsWith("A"))
            .sorted()
            .collect(Collectors.toList());
}
public  double getAverageAge(){
    return studentRepository.findAll().stream()
            .mapToInt(Student::getAge)
            .average()
            .orElseThrow(StudentNotFoundException::new);
}
}
