package com.flab.point.presentation;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.point.application.facade.PointManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/point")
@RestController
public class PointController {

    private final PointManager pointManager;

    public PointController(PointManager pointManager) {
        this.pointManager = pointManager;
    }

    @LoginCheck(authority = Role.USER)
    @GetMapping
    public CommonApiResponse getPoints(@Authentication AuthenticatedUser user) {
        var userPoints = pointManager.getPoints(user.getUserId());
        return CommonApiResponse.success(userPoints);
    }

    @PostMapping("/charging")
    public CommonApiResponse chargePoint() {

        return CommonApiResponse.success(null);
    }
}
