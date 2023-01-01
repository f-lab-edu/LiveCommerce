package com.flab.seller.application;


import com.flab.common.auth.AuthenticatedSeller;
import com.flab.common.auth.PasswordEncryptor;
import com.flab.seller.application.command.LoginSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.exception.SellerPasswordNotMatchedException;
import org.springframework.transaction.annotation.Transactional;



public class LoginSellerProcessor {

    private final SellerRepository sellerRepository;
    private final PasswordEncryptor passwordEncryptor;


    public LoginSellerProcessor(
            SellerRepository sellerRepository,
            PasswordEncryptor passwordEncryptor
    ) {
        this.sellerRepository = sellerRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Transactional
    public AuthenticatedSeller execute(LoginSellerCommand command) {
        var seller = sellerRepository.findByEmail(command.getEmail());

        if (!passwordCheck(command, seller)) {
            throw new SellerPasswordNotMatchedException();
        }

        return seller.toLoginInfo();
    }

    private boolean passwordCheck(LoginSellerCommand command, Seller loginSellerInfo) {
        return passwordEncryptor.match(command.getPassword(), loginSellerInfo.getPassword());
    }
}
