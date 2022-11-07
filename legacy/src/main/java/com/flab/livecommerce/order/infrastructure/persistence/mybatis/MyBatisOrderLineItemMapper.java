package com.flab.livecommerce.order.infrastructure.persistence.mybatis;

import com.flab.livecommerce.order.domain.OrderLineItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderLineItemMapper {

    void save(OrderLineItem orderLineItem);
}
