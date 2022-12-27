package com.flab.seller.application;


import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;
import static com.flab.common.auth.SessionConst.AUTH_STATUS;

import com.flab.common.auth.PasswordEncryptor;
import com.flab.common.auth.Role;
import com.flab.seller.application.command.LoginSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.SessionRepository;
import com.flab.seller.domain.exception.InvalidSellerException;
import com.flab.seller.domain.exception.SellerPasswordNotMatchedException;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;



public class LoginSellerProcessor {

    private final SellerRepository sellerRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final SessionRepository sessionRepository;

    private final Long sessionExpirationSec;
    private final HttpSession session;


    public LoginSellerProcessor(
            SellerRepository sellerRepository,
            PasswordEncryptor passwordEncryptor,
            SessionRepository sessionRepository,
            Long sessionExpirationSec, HttpSession session
    ) {
        this.sellerRepository = sellerRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.sessionRepository = sessionRepository;
        this.sessionExpirationSec = sessionExpirationSec;
        this.session = session;
    }

    @Transactional
    public String execute(LoginSellerCommand command) {
        var seller = sellerRepository.findByEmail(command.getEmail());

        if (seller.isEmpty()) {
            throw new InvalidSellerException();
        }

        if (!passwordCheck(command, seller)) {
            throw new SellerPasswordNotMatchedException();
        }

        var loginSellerInfo = seller.get().toLoginInfo();
        // 세션 정보 생성
        session.setAttribute(AUTH_SESSION_MEMBER, loginSellerInfo);
        session.setAttribute(AUTH_STATUS, Role.SELLER);
        // redis session 정보 저장
        loginSellerInfo.addSessionInfo(session.getId(), sessionExpirationSec);
        sessionRepository.save(session.getId(), loginSellerInfo);

        return session.getId();
    }

    private boolean passwordCheck(LoginSellerCommand command, Optional<Seller> loginSellerInfo) {
        return passwordEncryptor.match(command.getPassword(), loginSellerInfo.get().getPassword());
    }
}
