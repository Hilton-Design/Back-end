package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    @GetMapping("/kakao/callback")
    public void kakaoCallback(@RequestParam String code) {
        userService.kakaoLogin(code);
    }
}
