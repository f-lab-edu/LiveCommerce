package com.flab.livecommerce.infrastructure.user.persistence.mysql;

import com.flab.livecommerce.domain.user.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlUserRepository {

    private JdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public MysqlUserRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("user") // user 테이블에 삽입
            .usingGeneratedKeyColumns("id"); // id 컬럼의 값을 key 로 반환
    }

    public User save(User user) {
        return insertUser(user);
    }

    private User insertUser(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("email", user.getEmail())
            .addValue("password", user.getPassword())
            .addValue("nickname", user.getNickname());

        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();
        user.setId(id);

        return user;
    }

    private void updateUser(User user) {
        String sql = "update user set email=?, password=?, nickname=? where id=?";
        template.update(
            sql,
            user.getEmail(),
            user.getPassword(),
            user.getNickname(),
            user.getId()
        );
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
