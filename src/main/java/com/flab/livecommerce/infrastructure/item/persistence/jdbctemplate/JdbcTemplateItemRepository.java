package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

        String sql = "select * from item i "
            + "join item_option_group iog on i.id = iog.item_id "
            + "join item_option iop on iog.id = iop.item_option_group_id "
            + "where i.id = ?";

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
                ItemOptionGroup itemOptionGroup = new ItemOptionGroup(
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

                ItemOption itemOption = new ItemOption(
                    itemOptionGroup.getId(),
                    rs.getString("iop.name"),
                    rs.getInt("iop.ordering"),
                    rs.getLong("iop.price")
                );
                itemOption.setId(rs.getLong("iop.id"));
                itemOptionGroup.addItemOption(itemOption);
            }
            return item;
        });
    }

}
