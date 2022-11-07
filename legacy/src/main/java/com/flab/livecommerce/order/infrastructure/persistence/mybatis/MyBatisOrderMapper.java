package com.flab.livecommerce.order.infrastructure.persistence.mybatis;

import com.flab.livecommerce.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisOrderMapper {

    void save(Order order);

    Order findById(Long id);
}
