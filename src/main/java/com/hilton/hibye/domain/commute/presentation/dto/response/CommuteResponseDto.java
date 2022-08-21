package com.hilton.hibye.domain.commute.presentation.dto.response;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.global.Utils.DateUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommuteResponseDto {

    private String date;

    private String name;

    private String thumbnailImage;

    private String goToWorkTime;

    private String getOffWorkTime;

    public static CommuteResponseDto of(Commute commute) {
        return CommuteResponseDto.builder()
                .date(DateUtil.localDateTimeToStringDate(commute.getGoToWorkTime()))
                .name(commute.getUser().getName())
                .goToWorkTime(DateUtil.localDateTimeToStringTime(commute.getGoToWorkTime()))
                .getOffWorkTime(DateUtil.localDateTimeToStringTime(commute.getGetOffWorkTime()))
                .build();
    }
}
