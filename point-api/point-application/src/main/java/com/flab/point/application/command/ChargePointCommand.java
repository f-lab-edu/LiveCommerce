package com.flab.point.application.command;

public class ChargePointCommand {

    private String payMethod;
    private Integer chargeAmount;

    public Integer getChargeAmount() {
        return chargeAmount;
    }

    public ChargePointCommand(String payMethod, Integer chargeAmount) {
        this.payMethod = payMethod;
        this.chargeAmount = chargeAmount;
    }
}
