package com.sda.cinema.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CinemaMain {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        DatabaseConnection.closeOnExit(conn);
    }


}
