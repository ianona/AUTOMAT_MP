/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.FontMetrics;
import java.util.List;

/**
 *
 * @author ianona
 */
public class StatePanel extends JPanel {

    private String stateName, config, left, right;
    private boolean cur, isFinal;
    private int x, y;
    private static Font monoFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
    private static Font monoFont2 = new Font(Font.MONOSPACED, Font.BOLD, 11);
    private static int size = 70;

    public StatePanel(String stateName, String left, String right) {
        this.setDoubleBuffered(true);
        this.setOpaque(false);
        this.stateName = stateName;
        this.left = left;
        this.right = right;
        this.config = left+"/"+right;
        cur = false;
        isFinal = false;
        this.x = 0;
        this.y = 0;
    }
    
    public void setCur(boolean cur) {
        this.cur = cur;
    }
    
    public void setFinal(boolean f) {
        this.isFinal = f;
    }

    public boolean getCur() {
        return cur;
    }

    public String getStateName() {
        return stateName;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        if (!isFinal)
            g.fillOval(x-1, y-1, size+2, size+2);
        else{
            g.fillOval(x-3, y-3, size+6, size+6);
        }

        if (cur) {
            g.setColor(Color.YELLOW);
        } else if (isFinal) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(new Color(0,0,0,150));
        }
        g.fillOval(x, y, size, size);

        if (cur || isFinal) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        
        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(stateName);
        int h = fm.getAscent();
        g.drawString(stateName, (size/2) - (w / 2), (size/2) + (h / 4));
        
        g.setFont(monoFont2);
        w = fm.stringWidth(config);
        h = fm.getAscent();
        g.drawString(config, (size/2) - (w / 2), (size/2) + h);

        g.dispose();
    }
}
