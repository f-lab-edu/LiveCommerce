package com.flab.livecommerce.item.infrastructure.item.persistence.jdbctemplate;

<<<<<<< HEAD:toMove/infrastructure/item/persistence/jdbctemplate/JdbcTemplateItemRepository.java
import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.image.ItemImage;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemOption;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
=======
import com.flab.common.exception.EntityNotFoundException;
import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.item.domain.ItemOption;
import com.flab.livecommerce.item.domain.ItemOptionGroup;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/infrastructure/item/persistence/jdbctemplate/JdbcTemplateItemRepository.java
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

// TODO -> itemOptionGroup, itemOption, itemImage 없는 경우 넘김 처리 (-> JPA)

@Repository
public class JdbcTemplateItemRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item")
            .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Item save(Item item) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(item);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return item.setId(id);
    }

    public void deleteById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String optionSql = "DELETE FROM item_option WHERE item_option.item_id = :id";
        jdbcTemplate.update(optionSql, parameterSource);
        String optionGroupSql = "DELETE FROM item_option_group WHERE item_option_group.item_id = :id";
        jdbcTemplate.update(optionGroupSql, parameterSource);
        String itemSql = "DELETE FROM item WHERE item.id = :id";
        jdbcTemplate.update(itemSql, parameterSource);
    }

    public Item update(Item item, Long id) {
        String sql = "UPDATE item "
            + "SET name=:name, description=:description, price=:price, sales_price=:salesPrice, stock_quantity=:stockQuantity "
            + "WHERE item.id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("id", id)
            .addValue("name", item.getName())
            .addValue("description", item.getDescription())
            .addValue("price", item.getPrice())
            .addValue("salesPrice", item.getSalesPrice())
            .addValue("stockQuantity", item.getStockQuantity())
            .addValue("shopId", item.getShopId());

        jdbcTemplate.update(sql, parameterSource);
        return item.setId(id);
    }

    public Item findById(Long id) {
        String sql = "SELECT * FROM item i "
            + "JOIN item_option_group iog ON i.id = iog.item_id "
            + "JOIN item_option io ON iog.id = io.item_option_group_id "
            + "JOIN item_image im ON i.id = im.item_id "
            + "WHERE i.id = :id";

        Map<String, Object> parameter = Map.of("id", id);

        Item item = jdbcTemplate.query(sql, parameter, resultSetExtractor());

        if (item == null) {
            throw new EntityNotFoundException();
        }
        return item;
    }


    private ResultSetExtractor<Item> resultSetExtractor() {

        return (rs -> {
            Item item = null;
            ItemImage itemImage = null;
            ItemOptionGroup itemOptionGroup = null;
            Map<Long, ItemOptionGroup> itemOptionGroupMap = new HashMap<>();

            while (rs.next()) {
                if (item == null) {
                    item = new Item(
                        rs.getLong("seller_id"),
                        rs.getString("i.name"),
                        rs.getString("description"),
                        rs.getLong("price"),
                        rs.getLong("sales_price"),
                        rs.getInt("stock_quantity"));
                    item.setId(rs.getLong("id"));
                }

                long itemOptionGroupId = rs.getLong("iog.id");
                if (!itemOptionGroupMap.containsKey(itemOptionGroupId)) {
                    itemOptionGroup = new ItemOptionGroup(
                        rs.getLong("iog.item_id"),
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

                long itemImageId = rs.getLong("im.id");
                itemImage = new ItemImage(
                    rs.getLong("item_id"),
                    rs.getInt("ordering"),
                    rs.getString("name"),
                    rs.getString("url")
                );
                itemImage.setId(itemImageId);
                item.addItemImage(itemImage);
            }
            return item;
        });
    }
}
