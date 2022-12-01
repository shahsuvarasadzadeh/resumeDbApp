package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void foo() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "4321";
        Connection c = DriverManager.getConnection(url, username, password);
        Statement stm = c.createStatement();
        stm.execute("select * from user ");
        ResultSet rs = stm.getResultSet();

        while (rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            String surname=rs.getString("surname");
            String email=rs.getString("e-mail");
            String phone=rs.getString("phone");

            System.out.println("id: "+id);
            System.out.println("name: "+name);
            System.out.println("surname: "+surname);
            System.out.println("email: "+email);
            System.out.println("phone: "+phone);

            System.out.println("------------------------------------");

        }


    }

    public static void main(String[] args) throws Exception {
        foo();
    }
}