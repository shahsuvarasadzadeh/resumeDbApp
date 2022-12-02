package org.example.dao_inter;

import org.example.bean.User;

import java.util.List;

public interface UserDaoInter {

     List<User> getAll();

     public boolean updateUser(User u);
     public boolean removeUser(int id);

     public User getById(int id);


}
