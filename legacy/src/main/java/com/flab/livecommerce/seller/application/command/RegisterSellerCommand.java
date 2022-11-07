package com.flab.livecommerce.seller.application.command;

import com.flab.livecommerce.seller.domain.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterSellerCommand {

    private String name;
    private String businessNo;
    private String email;

    public Seller toEntity() {
        return new Seller(name, businessNo, email);
    }
}
