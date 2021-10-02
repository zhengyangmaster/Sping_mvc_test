package cn.zzy.service.Impl;

import cn.zzy.dao.RoleDao;
import cn.zzy.dao.UserDao;
import cn.zzy.domain.Role;
import cn.zzy.domain.User;
import cn.zzy.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> listAll() {
        List<User> all = userDao.findAll();
        //封装userList中的每一个User的Role属性
      for (User user :all){
          Long id = user.getId();
         List<Role> roleList = roleDao.findByid(id);
         user.setRoles(roleList);


      }

        return all;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //向sys_user表中存储信息
        Long userid = userDao.save(user);


        //向关联表中存储信息
        userDao.saveUserRoleRel(userid,roleIds);



    }

    @Override
    public void del(Long userId) {

        //先删除关系表
        userDao.delUserRoleRel(userId);
        //其次删除用户表内容
        userDao.del(userId);


    }

    @Override
    public User login(String username, String password) {
        try {
            User user =userDao.findByUserAndPassword(username,password);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;

        }


    }
}
