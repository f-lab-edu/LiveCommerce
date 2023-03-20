package com.flab.user.presentation;

import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.user.application.facade.UserManager;
import com.flab.user.application.result.UserResult;
import com.flab.user.presentation.request.CreateUserRequest;
import com.flab.user.presentation.request.LoginUserRequest;
import com.flab.user.presentation.request.UserEmailRequest;
import com.flab.user.presentation.response.UserResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public CommonApiResponse<UserResponse> signUp(@RequestBody @Valid CreateUserRequest request) {
        UserResult user = userManager.createUser(request.toCommand());
        return CommonApiResponse.success(UserResponse.from(user));
    }

    @PostMapping("/login")
    public CommonApiResponse<String> login(@RequestBody @Valid LoginUserRequest request) {
        String token = userManager.login(request.toCommand());
        return CommonApiResponse.success(token);
    }

    @LoginCheck(authority = Role.USER)
    @PostMapping("/logout")
    public CommonApiResponse<String> logout(@RequestHeader String authorization) {
        userManager.logout(authorization.replace("Bearer ", ""));
        return CommonApiResponse.success(UserResponse.logOut());
    }

    @PostMapping("/email/check")
    public CommonApiResponse<String> checkEmail(@RequestBody @Valid UserEmailRequest request) {
        userManager.checkEmail(request.getEmail());
        return CommonApiResponse.success(UserResponse.checkEmail());
    }
}
