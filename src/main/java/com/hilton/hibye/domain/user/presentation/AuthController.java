package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.presentation.dto.request.LoginUserRequestDto;
import com.hilton.hibye.domain.user.presentation.dto.response.TokenResponseDto;
import com.hilton.hibye.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public TokenResponseDto loginUser(
            @RequestBody @Valid LoginUserRequestDto request
    ) {
        return authService.loginUser(request);
    }

    @DeleteMapping
    public void logoutUser() {
        authService.logoutUser();
    }

    @PutMapping
    public TokenResponseDto getNewAccessToken(
            @RequestHeader(value = "Refresh-Token") String refreshToken
    ) {
        return authService.getNewAccessToken(refreshToken);
    }
}
