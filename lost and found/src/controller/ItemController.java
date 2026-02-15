package controller;

import dao.LostItemDAO;
import dao.FoundItemDAO;
import model.LostItem;
import model.FoundItem;
import util.ValidationUtil;

import java.util.List;

/**
 * Controller for item operations
 */
public class ItemController {
    
    private LostItemDAO lostItemDAO;
    private FoundItemDAO foundItemDAO;
    
    public ItemController() {
        this.lostItemDAO = new LostItemDAO();
        this.foundItemDAO = new FoundItemDAO();
    }
    
    /**
     * Report a lost item
     * @param userId User ID
     * @param itemName Item name
     * @param description Description
     * @param imageData Image data (can be null)
     * @param contactDetails Contact details
     * @return Success message or error message
     */
    public String reportLostItem(int userId, String itemName, String description, 
                                  byte[] imageData, String contactDetails) {
        // Validate inputs
        if (!ValidationUtil.isNotEmpty(itemName)) {
            return "Item name is required";
        }
        
        if (!ValidationUtil.isNotEmpty(description)) {
            return "Description is required";
        }
        
        if (!ValidationUtil.isNotEmpty(contactDetails)) {
            return "Contact details are required";
        }
        
        // Create lost item
        LostItem item = new LostItem(userId, itemName, description, imageData, contactDetails);
        boolean success = lostItemDAO.createLostItem(item);
        
        if (success) {
            return "SUCCESS";
        } else {
            return "Failed to report lost item. Please try again.";
        }
    }
    
    /**
     * Report a found item
     * @param userId User ID
     * @param itemName Item name
     * @param description Description
     * @param imageData Image data (can be null)
     * @param contactDetails Contact details
     * @return Success message or error message
     */
    public String reportFoundItem(int userId, String itemName, String description, 
                                   byte[] imageData, String contactDetails) {
        // Validate inputs
        if (!ValidationUtil.isNotEmpty(itemName)) {
            return "Item name is required";
        }
        
        if (!ValidationUtil.isNotEmpty(description)) {
            return "Description is required";
        }
        
        if (!ValidationUtil.isNotEmpty(contactDetails)) {
            return "Contact details are required";
        }
        
        // Create found item
        FoundItem item = new FoundItem(userId, itemName, description, imageData, contactDetails);
        boolean success = foundItemDAO.createFoundItem(item);
        
        if (success) {
            return "SUCCESS";
        } else {
            return "Failed to report found item. Please try again.";
        }
    }
    
    /**
     * Get all lost items
     * @return List of lost items
     */
    public List<LostItem> getAllLostItems() {
        return lostItemDAO.getAllLostItems();
    }
    
    /**
     * Get all found items
     * @return List of found items
     */
    public List<FoundItem> getAllFoundItems() {
        return foundItemDAO.getAllFoundItems();
    }
    
    /**
     * Get user's lost items
     * @param userId User ID
     * @return List of user's lost items
     */
    public List<LostItem> getUserLostItems(int userId) {
        return lostItemDAO.getLostItemsByUserId(userId);
    }
    
    /**
     * Get user's found items
     * @param userId User ID
     * @return List of user's found items
     */
    public List<FoundItem> getUserFoundItems(int userId) {
        return foundItemDAO.getFoundItemsByUserId(userId);
    }
    
    /**
     * Delete a lost item
     * @param itemId Item ID
     * @return true if deleted successfully
     */
    public boolean deleteLostItem(int itemId) {
        return lostItemDAO.deleteLostItem(itemId);
    }
    
    /**
     * Delete a found item
     * @param itemId Item ID
     * @return true if deleted successfully
     */
    public boolean deleteFoundItem(int itemId) {
        return foundItemDAO.deleteFoundItem(itemId);
    }
}
