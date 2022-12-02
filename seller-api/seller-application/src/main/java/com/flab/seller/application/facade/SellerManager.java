package com.flab.seller.application.facade;

import com.flab.seller.application.LoginSellerProcessor;
import com.flab.seller.application.CreateSellerProcessor;
import com.flab.seller.application.SearchSellerProcessor;
import com.flab.seller.application.command.LoginSellerCommand;
import com.flab.seller.application.command.CreateSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.domain.exception.DuplicatedSellerEmailException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SellerManager {

    private final CreateSellerProcessor createSellerProcessor;
    private final LoginSellerProcessor loginSellerProcessor;
    private final SearchSellerProcessor searchSellerProcessor;
    private final SellerRepository sellerRepository;


    public SellerManager(
        CreateSellerProcessor createSellerProcessor,
        LoginSellerProcessor loginSellerProcessor,
        SearchSellerProcessor searchSellerProcessor,
        SellerRepository sellerRepository
    ) {
        this.createSellerProcessor = createSellerProcessor;
        this.loginSellerProcessor = loginSellerProcessor;
        this.searchSellerProcessor = searchSellerProcessor;
        this.sellerRepository = sellerRepository;
    }

    public Seller registerSeller(CreateSellerCommand command) {
        return createSellerProcessor.execute(command);
    }

    public Seller searchSeller(Long id) {
        return searchSellerProcessor.execute(id);
    }

    public void checkEmailDuplicated(String email) {
        if (sellerRepository.existsByEmail(email)) {
            throw new DuplicatedSellerEmailException();
        }
    }

    public String login(
        LoginSellerCommand command,
        HttpSession session
    ) {
        return loginSellerProcessor.execute(command, session);
    }
}
