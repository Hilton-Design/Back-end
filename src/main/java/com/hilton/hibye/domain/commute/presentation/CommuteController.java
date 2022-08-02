package com.hilton.hibye.domain.commute.presentation;

import com.hilton.hibye.domain.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("{name}")   // TODO :: findByName()에서 getCurrentUser()로 변경
public class CommuteController {

    private final CommuteService commuteService;

    @PostMapping("/go-to-work")
    public void goToWork(
            @PathVariable(name = "name") String name
    ) {
        commuteService.goToWork(name);
    }

    @PostMapping("/get-off-work")
    public void getOffWork(
            @PathVariable String name
    ) {
        commuteService.getOffWork(name);
    }

//    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

}
