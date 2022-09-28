package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    public Item findById(Long id) {
        String sql = "select * from item where id = :id";

        Map<String, Long> parameters = Collections.singletonMap("id", id);
        Item item = template.queryForObject(sql, parameters, itemRowMapper());

        return item;
    }

    public List<ItemOptionGroup> findItemOptionSeries(Item item) {
        //todo 구현 필요
        return null;
    }


    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item(
                rs.getString("name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getInt("sales_price"),
                rs.getInt("stock_quantity")
            );

            return item.setId(rs.getLong("id"));
        };
    }
}
