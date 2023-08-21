package com.SkyPro.Kurs3_DZ2_3.service;

import com.SkyPro.Kurs3_DZ2_3.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    @Value("${path.to.avatars.folder}")
private Path pathToAvatars;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public  Long save(Long StudentId, MultipartFile multipartFile){

    }
}

