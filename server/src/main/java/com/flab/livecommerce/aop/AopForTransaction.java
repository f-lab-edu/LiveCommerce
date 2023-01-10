package com.flab.livecommerce.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * Aop 내에서 트랜잭션을 별도로 가져가기 위해 클래스 분리. @DistributedLock이 선언된 메소드의 로직을 수행하는데 사용
 * 동시성에 대한 처리는 부모 트랜잭션의 유무와 관계없이 별도의 트랜잭션으로 동작해야하기 때문에  @Transactional의 전파옵션은 propagation = Propagation.REQUIRES_NEW로 선언
 */
@Component
public class AopForTransaction {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
