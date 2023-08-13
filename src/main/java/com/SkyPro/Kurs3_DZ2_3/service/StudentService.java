package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {


    private final Map <Long, Student> map = new HashMap<>();
    private Long COUNTER = 1L;

    public Student getById(Long id) {
        return map.get(id);
    }

    public Collection<Student> getAll() {
        return map.values();

    }
    public Collection<Student>getByAge(int age){

        return map.values().stream()
                .filter(s->s.getAge()==age)
                .collect(Collectors.toList());
    }


    public Student create(Student student) {
        Long nextId = COUNTER++;
        student.setId(nextId);
        map.put(student.getId(), student);
        return student;
    }

    public Student update(Long id, Student student) {
        if (!map.containsKey(id)) {
            throw new DataNotFoundException();
        }
        Student existingStudent = map.get(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }

    public void delete (Long id){
        if (map.remove(id)==null) {
            throw new DataNotFoundException();
        }
    }


}
