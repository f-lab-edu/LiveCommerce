package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.User;
import com.flab.livecommerce.domain.UserRepository;
import com.flab.livecommerce.presentation.request.UserLoginRequest;

public class UserLoginProcessor {

	private final UserRepository userRepository;

	public UserLoginProcessor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 에러 존재하지 않는 유저
	 */

	public User execute(UserLoginRequest userLoginRequest) {

		User user = userRepository.findByEmail(userLoginRequest.getEmail());
		if (null == user) {
			throw new RuntimeException();
		}
		return user;
	}
}
