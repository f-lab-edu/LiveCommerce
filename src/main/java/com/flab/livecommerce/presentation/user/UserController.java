package com.flab.livecommerce.presentation.user;

import com.flab.livecommerce.application.user.facade.UserManager;
import com.flab.livecommerce.application.user.facade.UserTokenManager;
import com.flab.livecommerce.common.ApiResponse;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.infrastructure.user.annotation.LoginCheck;
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
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserManager userManager;
    private final UserTokenManager userTokenManager;

    public UserController(UserManager userManager, UserTokenManager userTokenManager) {
        this.userManager = userManager;
        this.userTokenManager = userTokenManager;
    }

    @PostMapping("/email/exists")
    public ApiResponse checkEmail(@RequestBody @Valid UserEmailRequest email) {
        userManager.checkEmailDuplicated(email.getEmail());
        return ApiResponse.success(null);
    }

    @PostMapping
    public ApiResponse signUp(@RequestBody @Valid UserCreateRequest request) {
        userManager.createUser(request.toCommand());
        return ApiResponse.success(null);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody @Valid UserLoginRequest request) {
        User loginUserInfo = userManager.login(request.toCommand());
        String token = userTokenManager.save(loginUserInfo);
        return ApiResponse.success(token);
    }

    @LoginCheck
    @PostMapping("/logout")
    public ApiResponse logout(@RequestHeader String authorization) {
        userTokenManager.delete(authorization.replace("Bearer ", ""));
        return ApiResponse.success(null);
    }
}
