package com.flab.livecommerce.presentation;

import com.flab.livecommerce.application.facade.UserManager;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.infrastructure.TokenAuthorization;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import com.flab.livecommerce.presentation.response.ApiResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/email/{email}/exists")
    public ApiResponse<Boolean> checkEmail(@PathVariable String email) {
        boolean checkEmail = userManager.checkEmailDuplicated(email);
        return ApiResponse.success(checkEmail);
    }

    @PostMapping
    public ApiResponse<Object> signUp(@RequestBody @Valid UserCreateRequest request) {

        userManager.createUser(request.toCommand());

        return ApiResponse.success(null);
    }

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody @Valid UserLoginRequest request) {
        User loginUser = userManager.login(request.toCommand());
        return ApiResponse.success(null);
    }

}
