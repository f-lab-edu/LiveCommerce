package com.flab.point.application.command;

public class ChargePointCommand {

    private String payMethod;
    private Long chargeAmount;

    public Long getChargeAmount() {
        return chargeAmount;
    }

    public ChargePointCommand(String payMethod, Long chargeAmount) {
        this.payMethod = payMethod;
        this.chargeAmount = chargeAmount;
    }
}
