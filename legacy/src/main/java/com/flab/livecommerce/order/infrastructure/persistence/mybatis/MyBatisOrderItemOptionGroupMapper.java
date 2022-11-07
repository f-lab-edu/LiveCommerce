package com.flab.livecommerce.order.infrastructure.persistence.mybatis;

import com.flab.livecommerce.order.domain.OrderItemOptionGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderItemOptionGroupMapper {

    void save(OrderItemOptionGroup orderItemOptionGroup);
}
