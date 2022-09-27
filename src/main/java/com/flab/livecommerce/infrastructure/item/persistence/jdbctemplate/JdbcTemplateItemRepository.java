package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.Item;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemRepository {

    private JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item") // item 테이블에 삽입
            .usingGeneratedKeyColumns("id"); // id 컬럼의 값을 key 로 반환
    }


    public Item save(Item item) {
        return insertItem(item);
    }

    private Item insertItem(Item item) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("name", item.getName())
            .addValue("description", item.getDescription())
            .addValue("price", item.getPrice())
            .addValue("sales_price", item.getSalesPrice())
            .addValue("stock_quantity", item.getStockQuantity())
            .addValue("model_number", item.getModelNumber());

        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return item.setId(id);
    }
}
