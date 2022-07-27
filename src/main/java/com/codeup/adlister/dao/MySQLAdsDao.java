package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public static void main(String[] args) {
        MySQLAdsDao my = new MySQLAdsDao(new Config());
        List<String> ss = my.all2();
        for(String s: ss){
            System.out.println(s);
        }
    }

//    public static void main(String[] args) {
//        MySQLAdsDao ads = new MySQLAdsDao(new Config());
//        Ad a = ads.findByAdID(3);
//        System.out.println(a.getTitle());
//        System.out.println(a.getId());
//        System.out.println(a.getUserId());
//        System.out.println(a.getDescription());
//    }

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public List<String> all2() {
        PreparedStatement stmt = null;
        List<String> testresults = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT description, title FROM ads");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                testresults.add(   rs.getString(1) +  rs.getString(2)    );
            }

            return testresults;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }
//
//    @Override
//    public Ad findById(Long id) {
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            return createAdsFromResults(rs).get(0);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving all ads.", e);
//        }
//    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public List<Ad> searchedAds(String search) {
        try {
            String query = "SELECT * FROM ads WHERE title like ? OR description LIKE ?";
            String searchTerm = "%" + search + "%";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, searchTerm);
            preparedStatement.setString(2, searchTerm);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createAdsFromResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
         }
     }    
     
    public boolean delete(long id) {
        try {
            String insertQuery = "DELETE FROM ads WHERE id = ?";
            String insertQuery2 = "DELETE FROM category_and_ad WHERE ad_id = ?";
            //delete from relationship table category_and_ad first before can delete ad
            PreparedStatement stmt = connection.prepareStatement(insertQuery2, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.execute();
            //delete ad
            stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, id);
            boolean didDelete = stmt.execute();
            return didDelete;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad.", e);
        }
    }

    @Override
    public void updateAdTitle(Long adId, String newTitle) {
        String query = "UPDATE ads SET title = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,newTitle);
            stmt.setLong(2,adId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public void updateAdDescription(Long adId, String newDescription) {
        String query = "UPDATE ads SET description = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,newDescription);
            stmt.setLong(2, adId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    public Ad findByAdID(long id) {
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
//            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ad to delete", e);
        }
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
