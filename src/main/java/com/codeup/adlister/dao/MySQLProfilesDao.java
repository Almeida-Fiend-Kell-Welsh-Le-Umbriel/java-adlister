package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.Profile;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProfilesDao implements  Profiles{
    private Connection connection = null;



    public MySQLProfilesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Profile> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM profiles");
            ResultSet rs = stmt.executeQuery();
            return createProfilesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }
    }

    private List<Profile> createProfilesFromResults(ResultSet rs) throws SQLException {
        List<Profile> pros = new ArrayList<>();
        while (rs.next()) {
            pros.add(extractProfile(rs));
        }
        return pros;
    }

    private Profile extractProfile(ResultSet rs) throws SQLException {
        return new Profile(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getLong("user_id")
        );
    }

    @Override
    public Long insert(Profile profile) {
        try {
            String insertQuery = "INSERT INTO profiles(first_name, last_name, address, phone, user_id) VALUES (?, ?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, profile.getFirst_name());
            stmt.setString(2, profile.getLast_name());
            stmt.setString(3, profile.getAddress());
            stmt.setString(4, profile.getPhone());
            stmt.setLong(5, profile.getUser_id());
            long n = stmt.executeUpdate();
            System.out.println("Total entries: " + n);
            return n;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad and category relation.", e);
        }
    }
}
