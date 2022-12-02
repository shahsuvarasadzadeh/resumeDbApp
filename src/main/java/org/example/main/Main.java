package org.example.main;

import org.example.bean.User;
import org.example.dao_impl.UserDaoImpl;
import org.example.dao_inter.UserDaoInter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
       UserDaoInter userDaoInter =Context.instanceUserDao();
//        List<User> list =userDaoInter.getAll();
//        System.out.println("List: "+list);

        User u=userDaoInter.getById(4);
        u.setName("Raheddin");
        userDaoInter.updateUser(u);
    }
}