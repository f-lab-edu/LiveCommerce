package com.flab.point.infrastructure.config;

import com.flab.point.application.ChargePointProcessor;
import com.flab.point.application.GetUserPointProcessor;
import com.flab.point.application.ReducePointProcessor;
import com.flab.point.domain.PointRepository;
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
            PointRepository pointRepository
    ) {
        return new ReducePointProcessor(pointRepository);
    }
}
