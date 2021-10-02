package cn.zzy.dao.Impl;

import cn.zzy.dao.UserDao;
import cn.zzy.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> query = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return query;
    }

    @Override
    public Long save(User user) {

        //jdbcTemplate.update("insert into sys_user values(?,?,?,?,?)", null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, null);
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getPhoneNum());

                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator, keyHolder);


        long userId = keyHolder.getKey().longValue();


        return userId;
    }

    @Override
    public void saveUserRoleRel(Long userid, Long[] roleIds) {
        for (Long roleid : roleIds) {

            jdbcTemplate.update("insert into sys_user_role values(?,?)", userid, roleid);
        }

    }

    @Override
    public void del(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?",userId);
    }

    @Override
    public void delUserRoleRel(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId=?",userId);

    }

    @Override
    public User findByUserAndPassword(String username, String password) throws EmptyResultDataAccessException {

        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
}
