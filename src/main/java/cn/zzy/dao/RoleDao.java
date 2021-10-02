package cn.zzy.dao;

import cn.zzy.domain.Role;

import java.util.List;

public interface RoleDao {
    public  List<Role> findAll();

    void save(Role role);

    List<Role> findByid(Long id);
}
