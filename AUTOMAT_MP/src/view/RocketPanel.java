/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ianona
 */
public class RocketPanel extends JPanel implements ActionListener {

    private Timer t = new Timer(2, this);
    private Image rocket;
    private int X, Y, dX, dY;
    private int velX = 1, velY = 1;
    String mode;
    private List<ItemPanel> items;
    private GameView gv;

    public RocketPanel(Rectangle r, GameView gv) {
        this.gv = gv;
        
        try {
            rocket = new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/rocket.png"))).getImage();
            prepareImage(rocket, this);
        } catch (IOException e) {
            System.out.println("file not found");
        }

        this.setLayout(null);

        items = new ArrayList<>();
        X = (int) r.getX();
        Y = (int) r.getY();
        mode = "right";
        this.setDoubleBuffered(true);
        this.setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < items.size(); i++) {
            if (mode == "left") {
                g2d.drawImage(items.get(i).getImage(), (X + i * 50) + 88 + 50, Y + 50, -(50), 50, null);
            } else {
                g2d.drawImage(items.get(i).getImage(), (X + i * 50) + 68, Y + 50, this);
            }
        }
        
        if (mode == "left") {
            g2d.drawImage(rocket, X + 255, Y, -(255), 153, null);
        } else {
            g2d.drawImage(rocket, X, Y, this);
        }
        revalidate();

        g.dispose();
    }

    public void addItem(ItemPanel i) {
        items.add(i);
    }

    public void removeItem(ItemPanel i) {
        items.remove(i);
    }
    
    public ItemPanel getItem(int i){
        return items.get(i);
    }
    
    public List<ItemPanel> getItems(){
        return items;
    }

    public String getMode() {
        return mode;
    }

    public Graphics2D rotate(Graphics2D g) {
        int x = this.getWidth() / 2;
        int y = this.getHeight() / 2;
        g.rotate(Math.toRadians(180.0), x, y);
        return g;
    }

    public void animate(Rectangle destination) {
        dX = (int) destination.getX();
        dY = (int) destination.getY();
        t.start();
    }

    public int getCapacity() {
        return items.size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (X == dX && Y == dY) {
            t.stop();

            if (mode.equalsIgnoreCase("right")) {
                mode = "left";
            } else {
                mode = "right";
            }
            gv.updateController();
            gv.emptyRocket();
            repaint();
        }

        if (X > dX) {
            X -= velX;
        }
        if (X < dX) {
            X += velX;
        }
        if (Y < dY) {
            Y += velY;
        }
        if (Y > dY) {
            Y -= velY;
        }

        repaint();
    }
}
