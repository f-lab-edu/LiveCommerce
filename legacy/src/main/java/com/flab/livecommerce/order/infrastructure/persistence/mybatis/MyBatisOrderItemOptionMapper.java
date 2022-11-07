package com.flab.livecommerce.order.infrastructure.persistence.mybatis;

import com.flab.livecommerce.order.domain.OrderItemOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderItemOptionMapper {

    void save(OrderItemOption orderItemOption);
}
