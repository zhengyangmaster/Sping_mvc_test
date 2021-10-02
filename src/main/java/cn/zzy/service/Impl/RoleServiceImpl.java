package cn.zzy.service.Impl;

import cn.zzy.dao.RoleDao;
import cn.zzy.domain.Role;
import cn.zzy.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> list=roleDao.findAll();

        return list;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
