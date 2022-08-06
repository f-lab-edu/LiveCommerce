package com.flab.livecommerce.presentation;

import com.flab.livecommerce.application.facade.UserManager;
import com.flab.livecommerce.domain.User;
import com.flab.livecommerce.infrastructure.Authorization;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

	private final UserManager userManager;
	private final Authorization authorization;

	public UserController(UserManager userManager, Authorization authorization) {
		this.userManager = userManager;
		this.authorization = authorization;
	}

	@PostMapping
	public String signUp(
		@RequestBody @Valid UserCreateRequest userCreateRequest) {
		userManager.createUser(userCreateRequest);

		return "ok";
	}


	@PostMapping("/login")
	public ResponseEntity<User> login(
		@RequestBody @Valid UserLoginRequest userLoginRequest,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		User loginUser = userManager.login(userLoginRequest);
		authorization.createToken(userLoginRequest, response);

		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}

}
