package com.SkyPro.Kurs3_DZ2_3.controller;

import com.SkyPro.Kurs3_DZ2_3.dto.AvatarDto;
import com.SkyPro.Kurs3_DZ2_3.model.Avatar;
import com.SkyPro.Kurs3_DZ2_3.service.AvatarService;
import com.SkyPro.Kurs3_DZ2_3.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private static final Logger logger = LoggerFactory.getLogger (AvatarController.class);
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> save (@RequestParam Long studentId,
                                      @RequestBody MultipartFile multipartFile)
    {
        try {
            Long avatarId = avatarService.save(studentId,multipartFile);
            return ResponseEntity.ok(avatarId);
                            }
    catch (IOException e){
            logger.error("failed to save avatar with id = "+ studentId,e);
            return  ResponseEntity.badRequest().build();

    }
    }
    @GetMapping("/page/{num}")
    public List<AvatarDto> getPage(@PathVariable("num")int pageNum){
        return avatarService.getPage(pageNum);
    }
}
