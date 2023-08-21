package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public ResponseEntity<Long> save (@RequestParam Long studentId,
                                      @RequestBody MultipartFile multipartFile)
    {
        try {
            Long avatarId = avatarService.save(studentId,multipartFile);
            return ResponseEntity.ok(avatarId);
                            }
    catch (IOException e){
           e.printStackTrace();
            return  ResponseEntity.badRequest().build();

    }
    }
}
