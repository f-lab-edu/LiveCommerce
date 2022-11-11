package com.flab.seller.application.command;


import com.flab.seller.domain.Seller;

public class RegisterSellerCommand {

    private String name;
    private String businessNo;
    private String email;

    public Seller toEntity() {
        return new Seller(name, businessNo, email);
    }

    public RegisterSellerCommand(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public String getEmail() {
        return email;
    }
}
