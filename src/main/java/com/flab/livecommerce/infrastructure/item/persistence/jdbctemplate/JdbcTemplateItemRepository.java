package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    public void deleteById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        String optionSql = "DELETE FROM item_option WHERE item_option.item_id = :id";
        jdbcTemplate.update(optionSql, param);
        String optionGroupSql = "DELETE FROM item_option_group WHERE item_option_group.item_id = :id";
        jdbcTemplate.update(optionGroupSql, param);
        String itemSql = "DELETE FROM item WHERE item.id = :id";
        jdbcTemplate.update(itemSql, param);
    }

    public Item update(Item item, Long id) {
        String sql = "UPDATE item "
            + "SET name=:name, description=:description, price=:price, sales_price=:salesPrice, stock_quantity=:stockQuantity "
            + "WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("shopId", item.getShopId())
            .addValue("name", item.getName())
            .addValue("description", item.getDescription())
            .addValue("price", item.getPrice())
            .addValue("salesPrice", item.getSalesPrice())
            .addValue("stockQuantity", item.getStockQuantity());

        jdbcTemplate.update(sql, param);
        return item.setId(id);
    }

    public Item findById(Long id) {
        String sql = "SELECT * FROM item i "
            + "JOIN item_option_group iog ON i.id = iog.item_id "
            + "JOIN item_option io ON iog.id = io.item_option_group_id "
            + "WHERE i.id = :id";

        Item item = jdbcTemplate.query(sql, resultSetExtractor(), id);

        if (item == null) {
            throw new EntityNotFoundException();
        }
        return item;
    }


    public List<ItemOptionGroup> findItemOptionSeries(Item item) {
        //todo 구현 필요
        var itemOptionGroupsList = item.getItemOptionGroups();

        return null;
    }

    private ResultSetExtractor<Item> resultSetExtractor() {

        return (rs -> {
            Item item = null;
            ItemOptionGroup itemOptionGroup = null;
            Map<Long, ItemOptionGroup> itemOptionGroupMap = new HashMap<>();

            while (rs.next()) {
                if (item == null) {
                    item = new Item(
                        rs.getLong("shop_id"),
                        rs.getString("i.name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("sales_price"),
                        rs.getInt("stock_quantity"));
                    item.setId(rs.getLong("id"));
                }

                long itemOptionGroupId = rs.getLong("iog.id");
                if (!itemOptionGroupMap.containsKey(itemOptionGroupId)) {
                    itemOptionGroup = new ItemOptionGroup(
                        rs.getLong("item_id"),
                        rs.getString("iog.name"),
                        rs.getInt("ordering"),
                        rs.getBoolean("basic"),
                        rs.getBoolean("exclusive"),
                        rs.getInt("minimum_choice"),
                        rs.getInt("maximum_choice")
                    );
                    itemOptionGroup.setId(rs.getLong("iog.id"));
                    item.addItemOptionGroup(itemOptionGroup);

                    itemOptionGroupMap.put(itemOptionGroupId, itemOptionGroup);
                }

                ItemOption itemOption = new ItemOption(
                    rs.getLong("iog.id"),
                    rs.getString("io.name"),
                    rs.getInt("io.ordering"),
                    rs.getLong("io.price"),
                    rs.getLong("i.id")
                );

                itemOption.setId(rs.getLong("io.id"));
                itemOptionGroup.addItemOption(itemOption);
            }
            return item;
        });
    }
}
