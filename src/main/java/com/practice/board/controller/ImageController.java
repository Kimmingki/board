package com.practice.board.controller;

import com.practice.board.dto.image.ImageUploadDTO;
import com.practice.board.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    /**
     * 프로필 사진 등록
     * @param imageUploadDTO 사진 정보
     * @param authentication 유저 정보
     * @return 프로필 페이지
     */
    @PostMapping("/upload")
    public String upload(@ModelAttribute ImageUploadDTO imageUploadDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        imageService.upload(imageUploadDTO, userDetails.getUsername());

        return "redirect:/member/info";
    }
}
