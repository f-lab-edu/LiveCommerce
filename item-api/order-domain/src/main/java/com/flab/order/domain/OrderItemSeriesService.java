package com.flab.order.domain;

import java.util.List;

public interface OrderItemSeriesService {

    List<OrderLineItem> save(Order order);
}
