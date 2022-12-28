package com.flab.point.application.facade;

import com.flab.point.application.ChargePointProcessor;
import com.flab.point.application.GetUserPointProcessor;
import com.flab.point.application.command.ChargePointCommand;
import org.springframework.stereotype.Service;

@Service
public class PointManager {

    private final ChargePointProcessor chargePointProcessor;
    private final GetUserPointProcessor getUserPointProcessor;

    public PointManager(
            ChargePointProcessor chargePointProcessor,
            GetUserPointProcessor getUserPointProcessor
    ) {
        this.chargePointProcessor = chargePointProcessor;
        this.getUserPointProcessor = getUserPointProcessor;
    }


    public Long getPoints(Long userId) {
        return getUserPointProcessor.execute(userId);
    }

    public void charge(Long userId, ChargePointCommand command) {
        chargePointProcessor.execute(userId, command);
    }
}
