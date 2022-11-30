package com.flab.seller.application.command;


import com.flab.seller.domain.Seller;

public class CreateSellerCommand {

    private String name;
    private String businessNo;
    private String email;
    private String password;

    public Seller toEntity(String encryptedPassword) {
        return new Seller(name, businessNo, email, encryptedPassword);
    }

    public CreateSellerCommand(String name, String businessNo, String email, String password) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
