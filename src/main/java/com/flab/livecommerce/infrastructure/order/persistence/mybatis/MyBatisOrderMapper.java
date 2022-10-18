package com.flab.livecommerce.infrastructure.order.persistence.mybatis;

import com.flab.livecommerce.domain.order.Order;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MyBatisOrderMapper {

    void save(Order order);

    Order findById(Long id);

    Order findOne(Long id);
}
