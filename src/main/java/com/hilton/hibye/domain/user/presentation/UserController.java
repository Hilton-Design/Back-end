package com.hilton.hibye.domain.user.presentation;

import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
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

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
