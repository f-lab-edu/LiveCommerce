package com.flab.livecommerce.presentation;

import com.flab.livecommerce.application.facade.UserManager;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String signUp(@RequestBody UserCreateRequest userCreateRequest) {
        userManager.createUser(userCreateRequest);
        return "create";
    }
}
