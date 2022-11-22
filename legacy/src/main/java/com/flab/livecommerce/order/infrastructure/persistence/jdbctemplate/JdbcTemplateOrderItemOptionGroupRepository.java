package com.flab.livecommerce.order.infrastructure.persistence.jdbctemplate;

import com.flab.livecommerce.order.domain.OrderItemOptionGroup;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateOrderItemOptionGroupRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateOrderItemOptionGroupRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("order_item_option_group")
            .usingGeneratedKeyColumns("id");
    }

    public OrderItemOptionGroup save(OrderItemOptionGroup orderItemOptionGroup) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(orderItemOptionGroup);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return orderItemOptionGroup.setId(id);
    }
}
