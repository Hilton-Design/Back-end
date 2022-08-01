package com.hilton.hibye.domain.commuting.presentation;

import com.hilton.hibye.domain.commuting.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("{name}")
public class CommuteController {

    private final CommuteService commuteService;

    @PostMapping("/go-to-work")
    public void goToWork(
            @PathVariable(name = "name") String name
    ) {
        commuteService.goToWork(name);
    }
}
