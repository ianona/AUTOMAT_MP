/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ianona
 */
public class ItemPanel extends JPanel {

    private Image item;
    private String type;
    String filename;
    private Image img;

    public ItemPanel(String type, String filename) {
        try {
            item = new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename))).getImage();
            prepareImage(item, this);
        } catch (IOException e) {
            System.out.println("file not found");
        }
        this.setDoubleBuffered(true);
        this.setOpaque(false);
        this.type = type;
        this.filename = filename;
        try {
            img = new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename))).getImage();
        } catch (IOException ex) {
            Logger.getLogger(ItemPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Image getImage(){
        return img;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(item, 0,0, this);
        g.dispose();
    }

    public String getType() {
        return type;
    }
    
    public String getFilename() {
        return filename;
    }
}
