package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.exception.ItemNotFoundException;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
        String optionSql = "DELETE FROM item_option WHERE item_option.item_id = :id";
        template.update(optionSql, param);
        String optionGroupSql = "DELETE FROM item_option_group WHERE item_option_group.item_id = :id";
        template.update(optionGroupSql, param);
        String itemSql = "DELETE FROM item WHERE item.id = :id";
        template.update(itemSql, param);
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

    public Item findById(Long id) {
        String sql = "SELECT * FROM item where id = :id";
        try {
            SqlParameterSource param = new MapSqlParameterSource("id", id);
            Item item = template.queryForObject(sql, param, itemRowMapper());
            return item;
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("상품 데이터가 존재하지 않습니다.");
        }
    }

    private RowMapper<Item> itemRowMapper() {
        return BeanPropertyRowMapper.newInstance(Item.class);
    }
}
