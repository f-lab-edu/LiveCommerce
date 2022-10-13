package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemOptionGroupRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemOptionGroupRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_option_group")
            .usingGeneratedKeyColumns("id");
    }

    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(itemOptionGroup);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();
        return itemOptionGroup.setId(id);
    }

    public Long update(ItemOptionGroup itemOptionGroup) {
        String sql = "UPDATE item_option_group SET item_id=:itemId, name=:name, ordering=:ordering, "
            + "basic=:basic, exclusive=:exclusive, minimum_choice=:minimumChoice, maximum_choice=:maximumChoice "
            + "WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", itemOptionGroup.getId())
            .addValue("itemId", itemOptionGroup.getItemId())
            .addValue("name", itemOptionGroup.getName())
            .addValue("ordering", itemOptionGroup.getOrdering())
            .addValue("basic", itemOptionGroup.isBasic())
            .addValue("exclusive", itemOptionGroup.isExclusive())
            .addValue("minimumChoice", itemOptionGroup.getMinimumChoice())
            .addValue("maximumChoice", itemOptionGroup.getMaximumChoice());
        template.update(sql, param);
        return itemOptionGroup.getId();
    }


    public ItemOptionGroup findById(Long itemOptionGroupId) {
        String sql = "SELECT id, item_id, name, basic, exclusive, minimum_choice, maximum_choice "
            + "FROM item_option_group "
            + "WHERE id = :id";
        Map<String, Object> param = Map.of("id", itemOptionGroupId);
        return template.queryForObject(sql, param, itemOptionGroupRowMapper());
    }

    private RowMapper<ItemOptionGroup> itemOptionGroupRowMapper() {
        return BeanPropertyRowMapper.newInstance(ItemOptionGroup.class);
    }
}
