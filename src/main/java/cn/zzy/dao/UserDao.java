package cn.zzy.dao;

import cn.zzy.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();


    Long  save(User user);

    void saveUserRoleRel(Long id, Long[] roleIds);

    void del(Long userId);

    void delUserRoleRel(Long userId);

    User findByUserAndPassword(String username, String password);
}
