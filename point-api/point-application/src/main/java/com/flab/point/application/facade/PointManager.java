package com.flab.point.application.facade;

import com.flab.point.application.ChargePointProcessor;
import com.flab.point.application.GetUserPointProcessor;
import com.flab.point.application.ReducePointProcessor;
import com.flab.point.application.command.ChargePointCommand;
import com.flab.point.application.command.ReducePointCommand;
import org.springframework.stereotype.Service;

@Service
public class PointManager {

    private final ChargePointProcessor chargePointProcessor;
    private final GetUserPointProcessor getUserPointProcessor;
    private final ReducePointProcessor reducePointProcessor;

    public PointManager(
            ChargePointProcessor chargePointProcessor,
            GetUserPointProcessor getUserPointProcessor,
            ReducePointProcessor reducePointProcessor
    ) {
        this.chargePointProcessor = chargePointProcessor;
        this.getUserPointProcessor = getUserPointProcessor;
        this.reducePointProcessor = reducePointProcessor;
    }


    public Integer getPoints(Long userId) {
        return getUserPointProcessor.execute(userId);
    }

    public Integer charge(Long userId, ChargePointCommand command) {
        return chargePointProcessor.execute(userId, command);
    }

    public Integer reduce(Long userId, ReducePointCommand command) {
        return reducePointProcessor.execute(userId, command);
    }
}
