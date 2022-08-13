package com.flab.livecommerce.domain.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionToken {

    private Long id;
    private String token;
    private LocalDateTime expirationDate;

    // TODO: 2022/08/12 토큰은 User 가 request 를 보내면, token을 확인하고 해당하는 token 세션 저장소에 로그인 유저 정보가 담겨 있으면 인증처리 한다.
    // TODO: 2022/08/12 그러기 위해 fk 설정이 필요할 것 같다.?
}
