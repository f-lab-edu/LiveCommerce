package com.flab.livecommerce.infrastructure.order.persistence.mybatis;

import com.flab.livecommerce.domain.order.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderRepository {

    void save(Order order);
}
