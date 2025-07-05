package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Entity.User;

import java.util.List;

public interface IUserService {

      void addUser(User user);
      User getUserById(Long id);
      List<User> getAllUsers();
}
