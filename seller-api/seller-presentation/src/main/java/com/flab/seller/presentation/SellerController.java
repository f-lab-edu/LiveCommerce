package com.flab.seller.presentation;

import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.seller.application.facade.SellerManager;
import com.flab.seller.presentation.request.CreateSellerRequest;
import com.flab.seller.presentation.request.LoginSellerRequest;
import com.flab.seller.presentation.request.SellerEmailRequest;
import javax.servlet.http.HttpServletRequest;
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

    public SellerController(
            SellerManager sellerManager
    ) {
        this.sellerManager = sellerManager;
    }

    @PostMapping("/signup")
    public CommonApiResponse signUp(@RequestBody @Valid CreateSellerRequest request) {
        sellerManager.registerSeller(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @PostMapping("/login")
    public CommonApiResponse login(
        @RequestBody @Valid LoginSellerRequest loginSellerRequest,
        HttpServletRequest request
    ) {
        var loginSellerInfo = sellerManager.login(loginSellerRequest.toCommand());

        /* TODO 인증 서비스 구현
        // 세션 정보 생성
        HttpSession session = request.getSession();
        session.setAttribute(AUTH_SESSION_MEMBER, loginSellerInfo);
        session.setAttribute(AUTH_STATUS, Role.SELLER);

        // redis session 정보 저장
        loginSellerInfo.addSessionInfo(session.getId(), sessionExpirationSec);
        sessionRepository.save(session.getId(), loginSellerInfo);

         */


        return CommonApiResponse.success(null);
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

