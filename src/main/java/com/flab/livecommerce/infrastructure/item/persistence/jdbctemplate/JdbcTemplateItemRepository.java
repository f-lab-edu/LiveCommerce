package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item") // item 테이블에 삽입
            .usingGeneratedKeyColumns("id"); // id 컬럼의 값을 key 로 반환
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Item save(Item item) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(item);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return item.setId(id);
    }

    public Item findById(Long id) {

        String sql = "select * from item, item_option_group"
            + " where item.id = item_option_group.item_id and item.id = ?";

        Item item = jdbcTemplate.query(sql, resultSetExtractor(), id);

        if (item == null) {
            throw new EntityNotFoundException("Item");
        }

        return item;
    }


    public List<ItemOptionGroup> findItemOptionSeries(Item item) {
        //todo 구현 필요
        var itemOptionGroupsList = item.getItemOptionGroups();

        return null;
    }

    private ResultSetExtractor<Item> resultSetExtractor() {
        RowMapper<Item> itemMapper = BeanPropertyRowMapper.newInstance(Item.class);
        RowMapper<ItemOptionGroup> itemOptionGroupRowMapper =
            BeanPropertyRowMapper.newInstance(ItemOptionGroup.class);

        return (rs -> {
            Item item = null;
            int row = 0;

            while (rs.next()) {
                if (item == null) {
                    item = itemMapper.mapRow(rs, row);
                }
                item.addItemOptionGroup(itemOptionGroupRowMapper.mapRow(rs, row));
                row++;
            }
            return item;
        });
    }


    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item(
                rs.getLong("shop_id"),
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
