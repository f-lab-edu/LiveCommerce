package com.flab.point.infrastructure.config;

import com.flab.point.application.ChargePointProcessor;
import com.flab.point.application.GetUserPointProcessor;
import com.flab.point.application.ReducePointProcessor;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.domain.PointTransactionService;
import com.flab.point.infrastructure.PointTransactionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointConfig {

    @Bean
    public ChargePointProcessor chargePointProcessor(
            PointRepository pointRepository
    ) {
        return new ChargePointProcessor(pointRepository);
    }

    @Bean
    public GetUserPointProcessor getUserPointProcessor(
           PointRepository pointRepository
    ) {
        return new GetUserPointProcessor(pointRepository);
    }

    @Bean
    public ReducePointProcessor reducePointProcessor(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository,
            PointTransactionService pointTransactionService
    ) {
        return new ReducePointProcessor(pointRepository, pointTransactionRepository, pointTransactionService);
    }

    @Bean
    public PointTransactionService pointTransactionService() {
        return new PointTransactionServiceImpl();
    }


}
