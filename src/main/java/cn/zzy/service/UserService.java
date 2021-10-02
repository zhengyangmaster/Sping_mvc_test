package cn.zzy.service;

import cn.zzy.domain.User;

import java.util.List;

public interface UserService {


   public List<User> listAll();

   public void save(User user, Long[] rolesid);

   void del(Long userId);

   User login(String username, String password);
}
