<<<<<<< HEAD:toMove/infrastructure/image/persistence/jdbctemplate/JdbcTemplateItemImageRepository.java
package com.flab.livecommerce.infrastructure.image.persistence.jdbctemplate;

import com.flab.livecommerce.domain.image.ItemImage;
=======
package com.flab.livecommerce.item.infrastructure.item.persistence.jdbctemplate;

import com.flab.livecommerce.item.domain.ItemImage;
import java.util.List;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/infrastructure/item/persistence/jdbctemplate/JdbcTemplateItemImageRepository.java
import javax.sql.DataSource;
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
            .withTableName("item_image")
            .usingGeneratedKeyColumns("id");
    }

    public ItemImage save(ItemImage itemImage) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(itemImage);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();
        itemImage.setId(id);
        return itemImage;
    }

    public void deleteAllById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String sql = "DELETE FROM item_image WHERE item_image.item_id = :id";
        template.update(sql, parameterSource);
    }

    public void updateOrdering(Long imageId, Integer order) {
        String sql = "UPDATE item_image "
            + "SET ordering=:ordering "
            + "WHERE id=:imageId";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("imageId", imageId)
            .addValue("ordering", order);

        template.update(sql, param);
    }
}
