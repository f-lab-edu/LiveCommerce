package com.flab.livecommerce.seller.application.facade;

import com.flab.livecommerce.seller.application.RegisterSellerProcessor;
import com.flab.livecommerce.seller.application.SearchSellerProcessor;
import com.flab.livecommerce.seller.application.command.RegisterSellerCommand;
import com.flab.livecommerce.seller.domain.Seller;
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
