package org.example.main;

import org.example.dao_impl.UserDaoImpl;
import org.example.dao_inter.UserDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao(){
       return new UserDaoImpl();
    }

}
