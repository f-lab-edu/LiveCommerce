package com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate;

import com.flab.livecommerce.domain.order.OrderLineItem;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateOrderLineItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateOrderLineItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("order_line_item")
            .usingGeneratedKeyColumns("id");
    }

    public OrderLineItem save(OrderLineItem orderLineItem) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(orderLineItem);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return orderLineItem.setId(id);
    }
}
