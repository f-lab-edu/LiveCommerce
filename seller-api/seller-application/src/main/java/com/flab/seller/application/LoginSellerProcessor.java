package com.flab.seller.application;


import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.common.auth.PasswordEncryptor;
import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.exception.ErrorCode;
import com.flab.seller.application.command.LoginSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.exception.SellerPasswordNotMatchedException;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

public class LoginSellerProcessor {

    private final SellerRepository sellerRepository;
    private final PasswordEncryptor passwordEncryptor;


    public LoginSellerProcessor(SellerRepository sellerRepository,
        PasswordEncryptor passwordEncryptor
    ) {
        this.sellerRepository = sellerRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Transactional
    public String execute(LoginSellerCommand command, HttpSession session) {
        Seller seller = sellerRepository.findByEmail(command.getEmail());

        if (seller == null) {
            throw new EntityNotFoundException(ErrorCode.SELLER_NOT_FOUND); // TODO 명구님 invalid를 써야 하는 이유 질문
        }

        if (!passwordCheck(command, seller)) {
            throw new SellerPasswordNotMatchedException();
        }

        AuthenticatedSeller loginsellerinfo = seller.toLoginInfo();
        session.setAttribute(AUTH_SESSION_MEMBER, loginsellerinfo);

        return session.getId();
    }

    private boolean passwordCheck(LoginSellerCommand command, Seller loginSellerInfo) {
        return passwordEncryptor.match(command.getPassword(), loginSellerInfo.getPassword());
    }
}
