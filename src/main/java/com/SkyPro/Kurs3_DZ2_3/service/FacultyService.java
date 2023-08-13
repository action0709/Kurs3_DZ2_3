package com.SkyPro.Kurs3_DZ2_3.service;


import com.SkyPro.Kurs3_DZ2_3.exception.DataNotFoundException;
import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> map = new HashMap<>();
    private Long COUNTER = 1L;

    public Faculty getById(Long id) {
        return map.get(id);
    }

    public Collection<Faculty> getAll() {
        return map.values();

    }
    public Collection<Faculty>getByColor(String color){

        return map.values().stream()
                .filter(s->s.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public Faculty create(Faculty faculty) {
        Long nextId = COUNTER++;
        faculty.setId(nextId);
        map.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty update(Long id, Faculty faculty) {
        if (!map.containsKey(id)) {
            throw new DataNotFoundException();
        }
        Faculty existingFaculty = map.get(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    public void delete (Long id){
        if (map.remove(id)==null) {
            throw new DataNotFoundException();
        }
    }
}

