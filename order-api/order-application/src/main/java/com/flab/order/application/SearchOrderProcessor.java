package com.flab.order.application;

import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;

public class SearchOrderProcessor {

    private final OrderRepository orderRepository;

    public SearchOrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id) {
        //todo transactional 어노테이션, 조인쿼리 작성해서 가져오도록 수정
        //반환타입은 Order 객체 그대로 반환하지 않도록 수정
        return orderRepository.findById(id);
    }
}
