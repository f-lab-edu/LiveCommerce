package com.flab.livecommerce.infrastructure.item.persistence.mysql;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlItemOptionGroupRepository {

    private JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public MysqlItemOptionGroupRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_option_group")
            .usingGeneratedKeyColumns("id");
    }

    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        return insertItemOptionGroup(itemOptionGroup);
    }

    private ItemOptionGroup insertItemOptionGroup(ItemOptionGroup itemOptionGroup) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("item_id", itemOptionGroup.getItemId())
            .addValue("name", itemOptionGroup.getName())
            .addValue("ordering", itemOptionGroup.getOrdering())
            .addValue("basic", itemOptionGroup.isBasic())
            .addValue("exclusive", itemOptionGroup.isExclusive())
            .addValue("minimum_choice", itemOptionGroup.getMinimumChoice())
            .addValue("maximum_choice", itemOptionGroup.getMaximumChoice());

        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return itemOptionGroup.setId(id);
    }
}
