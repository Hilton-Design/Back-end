package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @PostMapping
    public void signUp(@RequestBody CreateUserRequestDto request) {
        userService.signUp(request);
    }

    @GetMapping("/currentUser")
    public String getCurrentUser() {
        return userFacade.getCurrentUser().getEmail();
    }

    @GetMapping("/test")
    public Authentication test() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
