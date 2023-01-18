package com.flab.order.infrastructure.service;

import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.ItemQuantity;
import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.order.infrastructure.feign.OrderClient;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecreaseInventoryServiceV1 implements DecreaseInventoryService {

    private final OrderClient orderClient;
    private final Logger log = LoggerFactory.getLogger(DecreaseInventoryServiceV1.class);

    public DecreaseInventoryServiceV1(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public List<ItemQuantity> service(OrderPayedEvent event) {
        log.info(">>>>서비스 구현체 시작");
        return orderClient.decrease(event.getItemQuantities());
    }
}
