package com.flab.livecommerce.order.domain;

import java.util.List;

public interface OrderItemSeriesService {

    List<OrderLineItem> save(Order order);
}
