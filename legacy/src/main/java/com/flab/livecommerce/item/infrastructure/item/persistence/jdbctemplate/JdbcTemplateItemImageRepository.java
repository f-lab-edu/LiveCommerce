package com.flab.livecommerce.item.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.item.domain.ItemImage;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemImageRepository {
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemImageRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_image")
            .usingGeneratedKeyColumns("id");
    }

    public void saveAll(List<ItemImage> imageList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(imageList.toArray());
        jdbcInsert.executeBatch(batch);

        // TODO set id..
    }

    public void save(ItemImage thumbnail) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(thumbnail);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();
        thumbnail.setId(id);
    }
}