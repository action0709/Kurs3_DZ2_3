package com.SkyPro.Kurs3_DZ2_3.repository;

import com.SkyPro.Kurs3_DZ2_3.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    List<Faculty> findAllByColor(String color);
    List<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color,String name);

    Optional<Faculty> findByStudent_Id(Long studentId);
}
