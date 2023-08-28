package com.SkyPro.Kurs3_DZ2_3.repository;

import com.SkyPro.Kurs3_DZ2_3.model.Avatar;
import com.SkyPro.Kurs3_DZ2_3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository <Avatar,Long>{
    Optional<Avatar> findByStudent (Student student);


}
