package com.flab.common.domain;

import java.time.LocalDateTime;

/**
 * 모든 이벤트에서 구현되는 최소한의 인터페이스.
 * 모든 이벤트의 기본 계약을 강화 - iddd 382p
 */
public interface DomainEvent {

    LocalDateTime occurredOn();
}
