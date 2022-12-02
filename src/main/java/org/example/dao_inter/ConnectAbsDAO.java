package org.example.dao_inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectAbsDAO {

    public Connection connect() throws Exception {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "4321";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
