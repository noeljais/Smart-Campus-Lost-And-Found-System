package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Utility class for image operations
 */
public class ImageUtil {
    
    private static final int MAX_IMAGE_SIZE = 500; // Maximum dimension for display
    private static final int THUMBNAIL_SIZE = 150; // Thumbnail size
    
    /**
     * Convert byte array to ImageIcon
     * @param imageData Byte array of image data
     * @return ImageIcon or null if conversion fails
     */
    public static ImageIcon byteArrayToImageIcon(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            return null;
        }
        
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage bufferedImage = ImageIO.read(bis);
            
            if (bufferedImage != null) {
                return new ImageIcon(bufferedImage);
            }
        } catch (IOException e) {
            System.err.println("Error converting byte array to ImageIcon: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Convert File to byte array
     * @param file Image file
     * @return Byte array or null if conversion fails
     */
    public static byte[] fileToByteArray(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            
            fis.close();
            return bos.toByteArray();
            
        } catch (IOException e) {
            System.err.println("Error converting file to byte array: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Resize image to fit within max dimensions while maintaining aspect ratio
     * @param icon Original ImageIcon
     * @param maxWidth Maximum width
     * @param maxHeight Maximum height
     * @return Resized ImageIcon
     */
    public static ImageIcon resizeImage(ImageIcon icon, int maxWidth, int maxHeight) {
        if (icon == null) {
            return null;
        }
        
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        
        // Calculate scaling factor
        double scale = Math.min(
            (double) maxWidth / originalWidth,
            (double) maxHeight / originalHeight
        );
        
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);
        
        Image scaledImage = icon.getImage().getScaledInstance(
            newWidth, newHeight, Image.SCALE_SMOOTH
        );
        
        return new ImageIcon(scaledImage);
    }
    
    /**
     * Create thumbnail from ImageIcon
     * @param icon Original ImageIcon
     * @return Thumbnail ImageIcon
     */
    public static ImageIcon createThumbnail(ImageIcon icon) {
        return resizeImage(icon, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
    }
    
    /**
     * Create display-sized image from ImageIcon
     * @param icon Original ImageIcon
     * @return Display-sized ImageIcon
     */
    public static ImageIcon createDisplayImage(ImageIcon icon) {
        return resizeImage(icon, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE);
    }
    
    /**
     * Create a placeholder image when no image is available
     * @param width Width of placeholder
     * @param height Height of placeholder
     * @return Placeholder ImageIcon
     */
    public static ImageIcon createPlaceholder(int width, int height) {
        BufferedImage placeholder = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = placeholder.createGraphics();
        
        // Fill with light gray background
        g2d.setColor(new Color(240, 240, 240));
        g2d.fillRect(0, 0, width, height);
        
        // Draw border
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRect(0, 0, width - 1, height - 1);
        
        // Draw text
        g2d.setColor(new Color(150, 150, 150));
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String text = "No Image";
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2d.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 4);
        
        g2d.dispose();
        return new ImageIcon(placeholder);
    }
    
    /**
     * Validate if file is a valid image
     * @param file File to validate
     * @return true if valid image file
     */
    public static boolean isValidImageFile(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }
        
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
               name.endsWith(".png") || name.endsWith(".gif") || 
               name.endsWith(".bmp");
    }
    
    /**
     * Get file size in KB
     * @param file File to check
     * @return Size in KB
     */
    public static long getFileSizeKB(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        return file.length() / 1024;
    }
}
