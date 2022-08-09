package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.domain.user.presentation.dto.response.UserResponseDto;
import com.hilton.hibye.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @PostMapping
    public void signUp(@RequestBody CreateUserRequestDto request) {
        userService.signUp(request);
    }

    @GetMapping("/currentUser")
    public UserResponseDto getCurrentUserName() {
        System.out.println("현재 유저를 찾네??????????");
        return UserResponseDto.of(userFacade.getCurrentUser());
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
