package com.flab.livecommerce.infrastructure.order.persistence.mybatis;

import com.flab.livecommerce.domain.order.OrderLineItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderLineItemMapper {

    void save(OrderLineItem orderLineItem);
}