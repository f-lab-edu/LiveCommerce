package com.flab.order.domain;

import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.event.OrderPayedEvent;

public interface DecreaseInventoryService {

    DecreaseInventoryData service(OrderPayedEvent event);
}
