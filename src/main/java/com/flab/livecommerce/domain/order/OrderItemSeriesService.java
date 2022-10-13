package com.flab.livecommerce.domain.order;

import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import java.util.List;

public interface OrderItemSeriesService {

    List<OrderLineItem> save(RegisterOrderCommand command, Order order);
}
