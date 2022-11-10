package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.ItemOption;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemOptionRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemOptionRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_option")
            .usingGeneratedKeyColumns("id");
    }

    public ItemOption save(ItemOption itemOption) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(itemOption);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return itemOption.setId(id);
    }

    public void update(ItemOption itemOption, Long optionId) {
        String sql = "UPDATE item_option "
            + "SET ordering=:ordering, name=:name, price=:price "
            + "WHERE id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("id", optionId)
            .addValue("itemOptionGroupId", itemOption.getItemOptionGroupId())
            .addValue("ordering", itemOption.getOrdering())
            .addValue("name", itemOption.getName())
            .addValue("price", itemOption.getPrice());

        template.update(sql, param);
    }
}
