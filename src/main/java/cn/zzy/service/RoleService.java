package cn.zzy.service;

import cn.zzy.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list();

    void save(Role role);
}
