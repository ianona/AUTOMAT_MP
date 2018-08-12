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

/**
 *
 * @author ianona
 */
public class StatePanel extends JPanel {

    private String state;
    private boolean cur;
    private int x, y;
    private static Font monoFont = new Font(Font.MONOSPACED, Font.BOLD, 11);
    private static int size = 70;

    public StatePanel(String state, int x, int y) {
        this.setDoubleBuffered(true);
        this.setOpaque(false);
        this.state = state;
        cur = false;
        this.x = x;
        this.y = y;
    }

    public void setCur(boolean cur) {
        this.cur = cur;
    }

    public boolean getCur() {
        return cur;
    }

    public String getState() {
        return state;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(x-1, y-1, size+2, size+2);

        if (cur) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(new Color(0,0,0,150));
        }
        g.fillOval(x, y, size, size);

        if (cur) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        
        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth(state);
        int h = fm.getAscent();
        g.drawString(state, (size/2) - (w / 2), (size/2) + (h / 4));

        g.dispose();
    }
}
