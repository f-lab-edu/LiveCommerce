package com.flab.livecommerce.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.domain.item.ItemImage;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateItemImageRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateItemImageRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("item_image");
    }

    private static RowMapper<ItemImage> getItemImageRowMapper() {
        return (rs, rowNum) -> new ItemImage(
            rs.getLong("item_id"),
            rs.getInt("ordering"),
            rs.getString("name"),
            rs.getString("url")
        );
    }

    public void save(ItemImage image) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(image);
        jdbcInsert.execute(param);
    }

    public void deleteAllById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        String sql = "DELETE FROM item_image WHERE item_image.item_id = :id";
        template.update(sql, param);
    }

    public void deleteAll(Long itemId, List<Integer> ordering) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("itemId", itemId)
            .addValue("ordering", ordering);
        String sql = "DELETE FROM item_image AS ii WHERE ii.item_id = :itemId AND ii.ordering IN (:ordering)";

        template.update(sql, param);
    }

    // batchupdate 방식 -> pk 문제 -> 아래 방식으로 변경
    public void updateOrdering(Long imageId, List<Integer> orderList) {
        List<Object[]> batch = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Object[] values = new Object[]{
                orderList.get(i),
                imageId,
                i
            };
            batch.add(values);
        }

        String sql = "UPDATE item_image SET ordering=? WHERE id=? AND ordering=?";
        //template.batchUpdate(sql, batch);
    }


    public void updateOrder(Long imageId, Integer order) {
        String sql = "UPDATE item_image "
            + "SET ordering=:ordering "
            + "WHERE id=:imageId";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("imageId", imageId)
            .addValue("ordering", order);

        template.update(sql, param);
    }
}
