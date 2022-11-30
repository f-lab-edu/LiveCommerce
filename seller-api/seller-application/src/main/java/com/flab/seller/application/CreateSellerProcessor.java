package com.flab.seller.application;

import com.flab.common.auth.PasswordEncryptor;
import com.flab.seller.application.command.CreateSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.exception.DuplicatedSellerEmailException;

public class CreateSellerProcessor {

    private final SellerRepository sellerRepository;
    private final PasswordEncryptor passwordEncryptor;


    public CreateSellerProcessor(
        SellerRepository sellerRepository,
        PasswordEncryptor passwordEncryptor
    ) {
        this.sellerRepository = sellerRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public Seller execute(CreateSellerCommand command) {

        if (sellerRepository.existsByEmail(command.getEmail())) {
            throw new DuplicatedSellerEmailException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(command.getPassword());
        return this.sellerRepository.save(command.toEntity(encryptedPassword));
    }
}

