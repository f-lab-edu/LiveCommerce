package com.flab.livecommerce.infrastructure.item.persistence.mysql;

import com.flab.livecommerce.domain.item.Item;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlItemRepository {

    private JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public MysqlItemRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item") // item 테이블에 삽입
            .usingGeneratedKeyColumns("id"); // id 컬럼의 값을 key 로 반환
    }


    public Item save(Item item) {
        return insertItem(item);
    }

    public Item findById(Long id) {
        String sql = "select * from item where id = ?";
        List<Item> items = template.query(sql, itemRowMapper(), id);

        return DataAccessUtils.singleResult(items);
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

    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item(
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getInt("sales_price"),
                rs.getInt("stock_quantity"),
                rs.getInt("model_number")
            );
            return item.setId(rs.getLong("id"));
        };
    }
}
