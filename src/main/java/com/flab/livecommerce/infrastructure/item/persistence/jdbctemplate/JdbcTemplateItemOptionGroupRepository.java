package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return itemOptionGroup.setId(id);
    }
}
