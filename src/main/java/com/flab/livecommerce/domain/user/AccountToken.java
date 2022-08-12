package com.flab.livecommerce.domain.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountToken {

    private Long id;
    private String token;
    private LocalDateTime tokenDateTime;

    // TODO: 2022/08/12 토큰은 User 가 request 를 보내면 Map 에 저장되어있는 Token 이랑 비교하여 같으면 인증처리 한다.
    // TODO: 2022/08/12 그러기 위해 fk 설정이 필요할 것 같다.
}
