package com.flab.livecommerce.presentation.user;

import com.flab.livecommerce.application.user.facade.UserManager;
import com.flab.livecommerce.common.annotation.LoginCheck;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.user.request.UserCreateRequest;
import com.flab.livecommerce.presentation.user.request.UserEmailRequest;
import com.flab.livecommerce.presentation.user.request.UserLoginRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public CommonApiResponse signUp(@RequestBody @Valid UserCreateRequest request) {
        userManager.createUser(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @PostMapping("/login")
    public CommonApiResponse login(@RequestBody @Valid UserLoginRequest request) {
        var token = userManager.login(request.toCommand());
        return CommonApiResponse.success(token);
    }

    @LoginCheck
    @PostMapping("/logout")
    public CommonApiResponse logout(@RequestHeader String authorization) {
        userManager.delete(authorization.replace("Bearer ", ""));
        return CommonApiResponse.success(null);
    }

    @PostMapping("/email/exists")
    public CommonApiResponse checkEmail(@RequestBody @Valid UserEmailRequest email) {
        userManager.checkEmailDuplicated(email.getEmail());
        return CommonApiResponse.success(null);
    }
}
