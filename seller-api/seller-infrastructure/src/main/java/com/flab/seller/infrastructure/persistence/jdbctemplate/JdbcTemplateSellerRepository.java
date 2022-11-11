package com.flab.seller.infrastructure.persistence.jdbctemplate;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.seller.domain.Seller;
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
public class JdbcTemplateSellerRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateSellerRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("seller")
            .usingGeneratedKeyColumns("id");
    }

    public Seller save(Seller seller) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(seller);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return seller.setId(id);
    }

    public Seller findById(Long id) {
        String sql = "select * from seller where id = :id";

        Map<String, Long> parameters = Collections.singletonMap("id", id);
        Seller seller = template.queryForObject(sql, parameters, sellerRowMapper());

        if (seller == null) {
            throw new EntityNotFoundException();
        }

        return seller;
    }

    private RowMapper<Seller> sellerRowMapper() {
        return (rs, rowNum) -> {
            Seller seller = new Seller(
                rs.getString("name"),
                rs.getString("business_no"),
                rs.getString("email")
            );

            return seller.setId(rs.getLong("id"));
        };
    }
}
