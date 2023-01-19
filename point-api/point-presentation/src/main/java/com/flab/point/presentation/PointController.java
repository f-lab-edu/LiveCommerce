package com.flab.point.presentation;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.point.application.facade.PointManager;
import com.flab.point.presentation.request.ChargePointRequest;
import com.flab.point.presentation.request.ReducePointRequest;
import com.flab.point.presentation.response.ChargePointResponse;
import com.flab.point.presentation.response.GetPointResponse;
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

        return CommonApiResponse.success(new GetPointResponse(userPoints));
    }

    // TODO 이후 작업) 결제 모듈 연결
    @LoginCheck(authority = Role.USER)
    @PostMapping("/charging")
    public CommonApiResponse chargePoint(
            @Authentication AuthenticatedMember user,
            @RequestBody ChargePointRequest request
    ) {
        Integer totalPoints = pointManager.charge(user.getId(), request.toCommand());

        return CommonApiResponse.success(new ChargePointResponse(totalPoints));
    }

    @PostMapping("/reduce")
    public CommonApiResponse reducePoint(
            @Authentication AuthenticatedMember user,
            @RequestBody ReducePointRequest request
    ) {
        pointManager.reduce(user.getId(), request.toCommand());

        return CommonApiResponse.success(null);
    }
}
