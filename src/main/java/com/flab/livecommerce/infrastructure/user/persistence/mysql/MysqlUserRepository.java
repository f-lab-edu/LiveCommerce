package com.flab.livecommerce.infrastructure.user.persistence.mysql;

import com.flab.livecommerce.domain.user.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlUserRepository {

    private JdbcTemplate template;

    public MysqlUserRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public User save(User user) {
        String sql = "insert into user(id, email, password, nickname) value(?, ?, ?, ?)";
        template.update(
            sql,
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getNickname()
        );

        return user;
    }

    public User findByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<User> users = template.query(sql, userRowMapper(), email);
        return DataAccessUtils.singleResult(users);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("nickname")
            );
            return user;
        };
    }
}
