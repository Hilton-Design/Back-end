package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.domain.OAuthToken;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @PostMapping
    public void signUp(
            @RequestBody CreateUserRequestDto request
    ) {
        userService.signUp(request);
    }

    @GetMapping("/currentUser")
    public String getCurrentUserName() {
        return userFacade.getCurrentUser().getName();
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        return userService.kakaoLogin(code);
    }
}
