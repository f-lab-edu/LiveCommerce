package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.ItemOption;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemOptionRepository {

    private JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemOptionRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_option")
            .usingGeneratedKeyColumns("id");
    }

    public ItemOption save(ItemOption itemOption) {
        return insertItemOption(itemOption);
    }

    private ItemOption insertItemOption(ItemOption itemOption) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("item_option_group_id", itemOption.getItemOptionGroupId())
            .addValue("ordering", itemOption.getOrdering())
            .addValue("name", itemOption.getName())
            .addValue("price", itemOption.getPrice());

        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return itemOption.setId(id);
    }

}
