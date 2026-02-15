package dao;

import config.DatabaseConfig;
import model.FoundItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Found Item operations
 */
public class FoundItemDAO {
    
    /**
     * Create a new found item report
     * @param item FoundItem object
     * @return true if created successfully
     */
    public boolean createFoundItem(FoundItem item) {
        String sql = "INSERT INTO found_items (user_id, item_name, description, image_data, contact_details) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, item.getUserId());
            pstmt.setString(2, item.getItemName());
            pstmt.setString(3, item.getDescription());
            
            if (item.getImageData() != null) {
                pstmt.setBytes(4, item.getImageData());
            } else {
                pstmt.setNull(4, Types.BINARY);
            }
            
            pstmt.setString(5, item.getContactDetails());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating found item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get all found items
     * @return List of all found items
     */
    public List<FoundItem> getAllFoundItems() {
        List<FoundItem> items = new ArrayList<>();
        String sql = "SELECT f.item_id, f.user_id, f.item_name, f.description, f.image_data, " +
                     "f.contact_details, f.reported_date, f.status, u.full_name, u.email " +
                     "FROM found_items f " +
                     "JOIN users u ON f.user_id = u.user_id " +
                     "WHERE f.status = 'active' " +
                     "ORDER BY f.reported_date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                FoundItem item = new FoundItem();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setImageData(rs.getBytes("image_data"));
                item.setContactDetails(rs.getString("contact_details"));
                item.setReportedDate(rs.getTimestamp("reported_date"));
                item.setStatus(rs.getString("status"));
                item.setReporterName(rs.getString("full_name"));
                item.setReporterEmail(rs.getString("email"));
                items.add(item);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all found items: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * Get found items by user ID
     * @param userId User ID
     * @return List of user's found items
     */
    public List<FoundItem> getFoundItemsByUserId(int userId) {
        List<FoundItem> items = new ArrayList<>();
        String sql = "SELECT f.item_id, f.user_id, f.item_name, f.description, f.image_data, " +
                     "f.contact_details, f.reported_date, f.status, u.full_name, u.email " +
                     "FROM found_items f " +
                     "JOIN users u ON f.user_id = u.user_id " +
                     "WHERE f.user_id = ? " +
                     "ORDER BY f.reported_date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                FoundItem item = new FoundItem();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setImageData(rs.getBytes("image_data"));
                item.setContactDetails(rs.getString("contact_details"));
                item.setReportedDate(rs.getTimestamp("reported_date"));
                item.setStatus(rs.getString("status"));
                item.setReporterName(rs.getString("full_name"));
                item.setReporterEmail(rs.getString("email"));
                items.add(item);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting found items by user ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * Delete a found item
     * @param itemId Item ID
     * @return true if deleted successfully
     */
    public boolean deleteFoundItem(int itemId) {
        String sql = "DELETE FROM found_items WHERE item_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, itemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting found item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
