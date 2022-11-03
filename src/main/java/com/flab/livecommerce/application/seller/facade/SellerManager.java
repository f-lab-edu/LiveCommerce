package com.flab.livecommerce.application.seller.facade;

import com.flab.livecommerce.application.seller.RegisterSellerProcessor;
import com.flab.livecommerce.application.seller.SearchSellerProcessor;
import com.flab.livecommerce.application.seller.command.RegisterSellerCommand;
import com.flab.livecommerce.domain.seller.Seller;
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
