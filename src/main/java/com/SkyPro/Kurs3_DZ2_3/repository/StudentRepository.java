package com.SkyPro.Kurs3_DZ2_3.repository;

import com.SkyPro.Kurs3_DZ2_3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
