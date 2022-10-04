package com.flab.livecommerce.infrastructure.shop.persistence.jdbctemplate;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.shop.Shop;
import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateShopRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateShopRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("shop")
            .usingGeneratedKeyColumns("id");
    }

    public Shop save(Shop shop) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(shop);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return shop.setId(id);
    }

    public Shop findById(Long id) {
        String sql = "select * from shop where id = :id";

        Map<String, Long> parameters = Collections.singletonMap("id", id);
        Shop shop = template.queryForObject(sql, parameters, shopRowMapper());

        if (shop == null) {
            throw new EntityNotFoundException();
        }

        return shop;
    }

    private RowMapper<Shop> shopRowMapper() {
        return (rs, rowNum) -> {
            Shop shop = new Shop(
                rs.getString("name"),
                rs.getString("business_no"),
                rs.getString("email")
            );

            return shop.setId(rs.getLong("id"));
        };
    }
}
