package com.SkyPro.Kurs3_DZ2_3.service;

import com.SkyPro.Kurs3_DZ2_3.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    @Value("${path.to.avatars.folder}")
private Path pathToAvatars;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public  Long save(Long studentId, MultipartFile multipartFile){
        Files.createDirectories(pathToAvatars);
        String  originalFilename= multipartFile.getOriginalFilename();
        int dotIndex=originalFilename.lastIndexOf(".");
        String extension=originalFilename.substring(dotIndex);
        String fileName = studentId + extension;
        FileOutputStream fos=new FileOutputStream(pathToAvatars.toAbsolutePath() + "/"+ fileName);
        multipartFile.getInputStream().transferTo(fos);
        fos.close();

        // todo save to db

        return -1L;
    }
}


