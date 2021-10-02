package cn.zzy.dao.Impl;

import cn.zzy.dao.RoleDao;
import cn.zzy.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        List<Role> query = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return query;
    }

    @Override
    public void save(Role role) {
        jdbcTemplate.update("insert into sys_role values(?,?,?)", null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findByid(Long id) {
        return    jdbcTemplate.query("select * from sys_user_role ur ,sys_role r where ur.roleId=r.id and ur.userId=? ",new BeanPropertyRowMapper<Role>(Role.class),id);

    }
}
