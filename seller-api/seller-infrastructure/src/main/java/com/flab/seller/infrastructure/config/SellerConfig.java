package com.flab.seller.infrastructure.config;


import com.flab.seller.application.CreateSellerProcessor;
import com.flab.seller.application.LoginSellerProcessor;
import com.flab.seller.application.LogoutSellerProcessor;
import com.flab.seller.application.SearchSellerProcessor;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.SessionRepository;
import com.flab.seller.infrastructure.encryption.SellerSecurityPasswordEncoder;
import com.flab.seller.infrastructure.sessionproperties.SessionProperties;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SellerConfig {

    @Bean
    public CreateSellerProcessor createSellerProcessor(
            SellerRepository sellerRepository
    ) {
        return new CreateSellerProcessor(
                sellerRepository,
                new SellerSecurityPasswordEncoder(sellerEncodingAlgorithm()));
    }

    @Bean
    public LoginSellerProcessor loginSellerProcessor(
            SellerRepository sellerRepository,
            SessionRepository sessionRepository,
            SessionProperties sessionProperties,
            HttpSession session
    ) {
        return new LoginSellerProcessor(
                sellerRepository,
                new SellerSecurityPasswordEncoder(sellerEncodingAlgorithm()),
                sessionRepository,
                sessionProperties.getSessionExpirationSec(),
                session
        );
    }

    @Bean
    public LogoutSellerProcessor logoutSellerProcessor(
            HttpSession session,
            SessionRepository sessionRepository
    ) {
        return new LogoutSellerProcessor(
                session,
                sessionRepository
        );
    }

    @Bean
    public SearchSellerProcessor searchSellerProcessor(
            SellerRepository sellerRepository
    ) {
        return new SearchSellerProcessor(
                sellerRepository
        );
    }

    @Bean
    public PasswordEncoder sellerEncodingAlgorithm() {
        return new BCryptPasswordEncoder();
    }
}
