package com.flab.point.presentation;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.point.application.facade.PointManager;
import com.flab.point.presentation.request.ChargePointRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public CommonApiResponse getPoints(@Authentication AuthenticatedMember user) {
        var userPoints = pointManager.getPoints(user.getId());
        return CommonApiResponse.success(userPoints);
    }

    // TODO 명구님 pr 병합 후 결제 모듈 연결 예정
    @LoginCheck(authority = Role.USER)
    @PostMapping("/charging")
    public CommonApiResponse chargePoint(
            @Authentication AuthenticatedMember user,
            @RequestBody ChargePointRequest request
    ) {
        pointManager.charge(user.getId(), request.toCommand());
        return CommonApiResponse.success(null);
    }
}
