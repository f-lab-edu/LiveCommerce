package com.flab.livecommerce.presentation;

import com.flab.livecommerce.application.facade.UserManager;
import com.flab.livecommerce.presentation.dto.InputEmail;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import com.flab.livecommerce.presentation.shared.ApiResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/email/exists")
    public ApiResponse checkEmail(@RequestBody @Valid InputEmail email) {
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
        String token = userManager.login(request.toCommand());
        return ApiResponse.success(token);
    }
}
