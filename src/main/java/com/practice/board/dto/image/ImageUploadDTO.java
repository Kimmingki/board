package com.practice.board.dto.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDTO {

    private MultipartFile file;
}
