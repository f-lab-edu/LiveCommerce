package com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate;

import com.flab.livecommerce.domain.order.OrderItemOption;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateOrderItemOptionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateOrderItemOptionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("order_item_option")
            .usingGeneratedKeyColumns("id");
    }

    public OrderItemOption save(OrderItemOption orderItemOption) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(orderItemOption);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return orderItemOption.setId(id);
    }
}
