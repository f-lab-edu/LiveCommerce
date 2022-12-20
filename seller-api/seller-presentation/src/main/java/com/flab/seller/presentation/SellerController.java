package com.flab.seller.presentation;

import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.seller.application.facade.SellerManager;
import com.flab.seller.presentation.request.CreateSellerRequest;
import com.flab.seller.presentation.request.LoginSellerRequest;
import com.flab.seller.presentation.request.SellerEmailRequest;
import com.flab.seller.presentation.response.LoginSellerResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    private final SellerManager sellerManager;

    public SellerController(SellerManager sellerManager) {
        this.sellerManager = sellerManager;
    }

    @PostMapping("/signup")
    public CommonApiResponse signUp(@RequestBody @Valid CreateSellerRequest request) {
        sellerManager.registerSeller(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @PostMapping("/login")
    public CommonApiResponse login(
        @RequestBody @Valid LoginSellerRequest request
    ) {
        String jsessionId = sellerManager.login(request.toCommand());
        return CommonApiResponse.success(new LoginSellerResponse(jsessionId));
    }

    @LoginCheck(authority = Role.SELLER)
    @PostMapping("/logout")
    public CommonApiResponse logout() {
        sellerManager.logout();
        return CommonApiResponse.success(null);
    }

    @PostMapping("/email/exists")
    public CommonApiResponse checkEmail(@RequestBody @Valid SellerEmailRequest email) {
        sellerManager.checkEmailDuplicated(email.getEmail());
        return CommonApiResponse.success(null);
    }


    @GetMapping("/{sellerId}")
    public CommonApiResponse searchSeller(@PathVariable("sellerId") Long id) {
        //sellerManager.searchSeller(id);
        return CommonApiResponse.success(null);
    }
}

