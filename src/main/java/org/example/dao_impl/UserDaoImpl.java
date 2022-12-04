package org.example.dao_impl;

import org.example.bean.User;
import org.example.dao_inter.ConnectAbsDAO;
import org.example.dao_inter.UserDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends ConnectAbsDAO implements UserDaoInter {
    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("select * from user ");
            ResultSet rs = stm.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                result.add(new User(id, name, surname, email, phone));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stm = c.prepareStatement("update user set name = ?,surname=?,email=?,phone=? where id=? ");
            stm.setString(1, u.getName());
            stm.setString(2, u.getSurname());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getPhone());
            stm.setInt(5, u.getId());
            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stm = c.prepareStatement("insert into user (name,surname,email,phone) values (?,?,?,?)");
            stm.setString(1, u.getName());
            stm.setString(2, u.getSurname());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getPhone());
            return stm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("delete  from user WHERE id=1 ");
            ResultSet rs = stm.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("select * from user where id="+userId);
            ResultSet rs = stm.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                result=new User(id, name, surname, email, phone);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
