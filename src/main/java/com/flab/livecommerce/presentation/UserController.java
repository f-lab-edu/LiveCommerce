package com.flab.livecommerce.presentation;

import com.flab.livecommerce.application.facade.UserManager;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.infrastructure.TokenAuthorization;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserManager userManager;
    private final TokenAuthorization tokenAuthorization;

    public UserController(UserManager userManager, TokenAuthorization authorization) {
        this.userManager = userManager;
        this.tokenAuthorization = authorization;
    }

    @PostMapping
    public String signUp(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        userManager.createUser(userCreateRequest);

        return "ok";
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
        @RequestBody @Valid UserLoginRequest userLoginRequest,
        HttpServletResponse response
    ) {
        User loginUser = userManager.login(userLoginRequest);
        tokenAuthorization.createToken(userLoginRequest, response);

        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

}
