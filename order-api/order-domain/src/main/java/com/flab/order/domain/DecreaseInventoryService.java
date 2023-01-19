package com.flab.order.domain;

import com.flab.order.domain.event.OrderPayedEvent;
import java.util.List;

public interface DecreaseInventoryService {

    void service(OrderPayedEvent event);
}
