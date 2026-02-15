package model;

import java.sql.Timestamp;

/**
 * Lost Item model class
 */
public class LostItem {
    private int itemId;
    private int userId;
    private String itemName;
    private String description;
    private byte[] imageData;
    private String contactDetails;
    private Timestamp reportedDate;
    private String status;
    
    // For display purposes
    private String reporterName;
    private String reporterEmail;
    
    // Constructors
    public LostItem() {}
    
    public LostItem(int userId, String itemName, String description, 
                    byte[] imageData, String contactDetails) {
        this.userId = userId;
        this.itemName = itemName;
        this.description = description;
        this.imageData = imageData;
        this.contactDetails = contactDetails;
        this.status = "active";
    }
    
    // Getters and Setters
    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public byte[] getImageData() {
        return imageData;
    }
    
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    
    public String getContactDetails() {
        return contactDetails;
    }
    
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
    public Timestamp getReportedDate() {
        return reportedDate;
    }
    
    public void setReportedDate(Timestamp reportedDate) {
        this.reportedDate = reportedDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getReporterName() {
        return reporterName;
    }
    
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
    
    public String getReporterEmail() {
        return reporterEmail;
    }
    
    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }
    
    @Override
    public String toString() {
        return "LostItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", reportedDate=" + reportedDate +
                '}';
    }
}
