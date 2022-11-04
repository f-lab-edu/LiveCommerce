package com.flab.livecommerce.order.infrastructure.persistence.jdbctemplate;

import com.flab.livecommerce.order.domain.Order;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateOrderRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateOrderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("orders")
            .usingGeneratedKeyColumns("id");
    }

    public Order save(Order order) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(order);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return order.setId(id);
    }
}
