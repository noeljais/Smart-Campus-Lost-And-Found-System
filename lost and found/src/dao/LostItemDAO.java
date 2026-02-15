package dao;

import config.DatabaseConfig;
import model.LostItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Lost Item operations
 */
public class LostItemDAO {
    
    /**
     * Create a new lost item report
     * @param item LostItem object
     * @return true if created successfully
     */
    public boolean createLostItem(LostItem item) {
        String sql = "INSERT INTO lost_items (user_id, item_name, description, image_data, contact_details) " +
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
            System.err.println("Error creating lost item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get all lost items
     * @return List of all lost items
     */
    public List<LostItem> getAllLostItems() {
        List<LostItem> items = new ArrayList<>();
        String sql = "SELECT l.item_id, l.user_id, l.item_name, l.description, l.image_data, " +
                     "l.contact_details, l.reported_date, l.status, u.full_name, u.email " +
                     "FROM lost_items l " +
                     "JOIN users u ON l.user_id = u.user_id " +
                     "WHERE l.status = 'active' " +
                     "ORDER BY l.reported_date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                LostItem item = new LostItem();
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
            System.err.println("Error getting all lost items: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * Get lost items by user ID
     * @param userId User ID
     * @return List of user's lost items
     */
    public List<LostItem> getLostItemsByUserId(int userId) {
        List<LostItem> items = new ArrayList<>();
        String sql = "SELECT l.item_id, l.user_id, l.item_name, l.description, l.image_data, " +
                     "l.contact_details, l.reported_date, l.status, u.full_name, u.email " +
                     "FROM lost_items l " +
                     "JOIN users u ON l.user_id = u.user_id " +
                     "WHERE l.user_id = ? " +
                     "ORDER BY l.reported_date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LostItem item = new LostItem();
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
            System.err.println("Error getting lost items by user ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * Delete a lost item
     * @param itemId Item ID
     * @return true if deleted successfully
     */
    public boolean deleteLostItem(int itemId) {
        String sql = "DELETE FROM lost_items WHERE item_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, itemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting lost item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
