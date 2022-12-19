package org.example.dao_impl;

import org.example.bean.Country;
import org.example.bean.User;
import org.example.dao_inter.ConnectAbsDAO;
import org.example.dao_inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends ConnectAbsDAO implements UserDaoInter {

    private User getSkill(ResultSet rs)throws Exception{

        int id=rs.getInt("id");
        String name=rs.getString("name");


        return getAllSkill();
    }


    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, nationalityStr, null);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);


        return new User(id, name, surname, email, phone, birthdate, nationality, birthplace);
    }


    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("SELECT " +
                    " u.* ," +
                    " n.name as nationality, " +
                    " c.nationality as birthplace " +
                    " from user u " +
                    " LEFT JOIN country n on u.nationality_id=n.id " +
                    " LEFT JOIN country c on u.birthplace_id=c.id");
            ResultSet rs = stm.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
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
    public User getAllSkill() {
        User result = null;
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("select * from skill" );
            ResultSet rs = stm.getResultSet();

            while (rs.next()) {
                result = getUser(rs);

            }

            } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {

            Statement stm = c.createStatement();
            stm.execute("SELECT " +
                    " u.* ," +
                    " n.name as nationality, " +
                    " c.nationality as birthplace " +
                    " from user u " +
                    " LEFT JOIN country n on u.nationality_id=n.id " +
                    " LEFT JOIN country c on u.birthplace_id=c.id where id=" + userId);
            ResultSet rs = stm.getResultSet();

            while (rs.next()) {
                result = getUser(rs);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
