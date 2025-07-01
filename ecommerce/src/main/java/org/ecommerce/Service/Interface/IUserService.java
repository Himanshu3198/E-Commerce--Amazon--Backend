package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.User;

import java.util.List;

public interface IUserService {

      void addUser(User user);
      User getUserById(Long id);
      List<User> getAllUsers();
}
