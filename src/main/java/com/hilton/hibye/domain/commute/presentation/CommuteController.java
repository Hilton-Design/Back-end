package com.hilton.hibye.domain.commute.presentation;

import com.hilton.hibye.domain.commute.presentation.dto.response.CommuteResponseDto;
import com.hilton.hibye.domain.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
// TODO :: findByName()에서 getCurrentUser()로 변경
public class CommuteController {

    private final CommuteService commuteService;

    @PostMapping("/go-to-work")
    public void goToWork(
    ) {
        commuteService.goToWork();
    }

    @PostMapping("/get-off-work")
    public void getOffWork(
    ) {
        commuteService.getOffWork();
    }

    @GetMapping("/today-list")
    public Page<CommuteResponseDto> getTodayList(
            @PageableDefault(
                    size = 5, sort = "id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return commuteService.getTodayList(pageable);
    }

    @GetMapping("/yesterday-list")
    public Page<CommuteResponseDto> getYesterdayList(
            @PageableDefault(
                    size = 5, sort = "id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return commuteService.getYesterdayList(pageable);
    }

    @GetMapping("/list")
    public Page<CommuteResponseDto> getCommuteList(
            @PageableDefault(
                    size = 5, sort = "id",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return commuteService.getCommuteList(pageable);
    }
}
