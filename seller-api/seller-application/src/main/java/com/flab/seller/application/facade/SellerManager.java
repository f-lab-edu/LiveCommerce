package com.flab.seller.application.facade;

import com.flab.seller.application.RegisterSellerProcessor;
import com.flab.seller.application.SearchSellerProcessor;
import com.flab.seller.application.command.RegisterSellerCommand;
import com.flab.seller.domain.Seller;
import org.springframework.stereotype.Service;

@Service
public class SellerManager {

    private final RegisterSellerProcessor registerSellerProcessor;
    private final SearchSellerProcessor searchSellerProcessor;


    public SellerManager(
        RegisterSellerProcessor registerSellerProcessor,
        SearchSellerProcessor searchSellerProcessor
    ) {
        this.registerSellerProcessor = registerSellerProcessor;
        this.searchSellerProcessor = searchSellerProcessor;
    }

    public Seller registerSeller(RegisterSellerCommand command) {
        return registerSellerProcessor.execute(command);
    }

    public Seller searchSeller(Long id) {
        return searchSellerProcessor.execute(id);
    }
}
