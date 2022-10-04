package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.Item;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item") // item 테이블에 삽입
            .usingGeneratedKeyColumns("id"); // id 컬럼의 값을 key 로 반환
    }


    public Item save(Item item) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(item);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return item.setId(id);
    }

    public void deleteById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        String sql = "DELETE item, iog, io FROM item "
            + "INNER JOIN item_option_group AS iog INNER JOIN item_option AS io "
            + "WHERE item.id = iog.item_id AND iog.id = io.item_option_group_id "
            + "AND item.id = :id";

        template.update(sql, param);
    }

    public void update(Item item, Long id) {
        String sql = "UPDATE item "
            + "SET name=:name, description=:description, price=:price, sales_price=:salesPrice, stock_quantity=:stockQuantity "
            + "WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("name", item.getName())
            .addValue("description", item.getDescription())
            .addValue("price", item.getPrice())
            .addValue("salesPrice", item.getSalesPrice())
            .addValue("stockQuantity", item.getStockQuantity());

        template.update(sql, param);
    }
}
