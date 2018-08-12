/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

/**
 *
 * @author ianona
 */
public class GameView extends JFrame {

    private GameController controller;
    private JLabel bgImage, rightPnl, leftPnl, RBtn, LBtn, helpBtn, infoBtn;
    private JLayeredPane mainPane;
    private RocketPanel rocketPnl;
    private ItemPanel human1, human2, lion, cow, rice;

    private static final Rectangle leftSide = new Rectangle(30, 120, 255, 153);
    private static final Rectangle rightSide = new Rectangle(420, 120, 255, 153);

    public GameView(GameController gc) {
        this.setSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("AUTOMAT MP");
        controller = gc;

        mainPane = new JLayeredPane();
        mainPane.setPreferredSize(new Dimension(700, 500));

        try {
            bgImage = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/bg.png"))));
            leftPnl = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/panel.png"))));
            rightPnl = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/panel2.png"))));
            RBtn = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/Rbtn.png"))));
            LBtn = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/LBtn.png"))));
            helpBtn = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/help.png"))));
            infoBtn = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/info.png"))));
        } catch (IOException e) {
            System.out.println("file not found");
        }

        bgImage.setBounds(0, 0, 700, 500);
        leftPnl.setBounds(0, 350, 297, 77);
        rightPnl.setBounds(410, 350, 297, 77);
        mainPane.add(bgImage, new Integer(0));
        mainPane.add(leftPnl, new Integer(100));
        mainPane.add(rightPnl, new Integer(100));

        rocketPnl = new RocketPanel(leftSide);
        rocketPnl.setBounds(0, 60, 700, 260);
        mainPane.add(rocketPnl, new Integer(200));
        rocketPnl.addMouseListener(new Item_click_rocket());

        LBtn.setBounds(305, 340, 95, 95);
        RBtn.setBounds(305, 340, 95, 95);
        mainPane.add(RBtn, new Integer(100));
        mainPane.add(LBtn, new Integer(100));
        LBtn.setVisible(false);

        RBtn.addMouseListener(new RBtn_click());
        LBtn.addMouseListener(new LBtn_click());

        human1 = new ItemPanel("human", "resources/boy.png");
        human2 = new ItemPanel("human", "resources/girl.png");
        lion = new ItemPanel("human", "resources/lion.png");
        cow = new ItemPanel("human", "resources/cow.png");
        rice = new ItemPanel("human", "resources/rice.png");

        human1.setBounds(0, 365, 50, 50);
        human2.setBounds(50, 365, 50, 50);
        lion.setBounds(100, 365, 50, 50);
        cow.setBounds(150, 365, 50, 50);
        rice.setBounds(200, 365, 50, 50);

        mainPane.add(human1, new Integer(200));
        mainPane.add(human2, new Integer(200));
        mainPane.add(lion, new Integer(200));
        mainPane.add(cow, new Integer(200));
        mainPane.add(rice, new Integer(200));

        human1.addMouseListener(new Item_click());
        human2.addMouseListener(new Item_click());
        lion.addMouseListener(new Item_click());
        cow.addMouseListener(new Item_click());
        rice.addMouseListener(new Item_click());

        helpBtn.setBounds(645, 10, 40, 40);
        infoBtn.setBounds(610, 10, 40, 40);
        helpBtn.addMouseListener(new helpBtn_click());
        infoBtn.addMouseListener(new infoBtn_click());
        mainPane.add(helpBtn, new Integer(100));
        mainPane.add(infoBtn, new Integer(100));

        this.add(mainPane);
        this.setVisible(true);
        this.setResizable(false);
    }

    class RBtn_click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            rocketPnl.animate(rightSide);
            RBtn.setVisible(false);
            LBtn.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class LBtn_click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            rocketPnl.animate(leftSide);
            LBtn.setVisible(false);
            RBtn.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class Item_click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            ItemPanel clicked = (ItemPanel) e.getSource();
            ItemPanel item = new ItemPanel(clicked.getType(), clicked.getFilename());
            item.setBounds(30, 180, 50, 50);

            if (rocketPnl.getCapacity() < 2) {
                rocketPnl.addItem(item);
                clicked.setVisible(false);
                rocketPnl.revalidate();
                rocketPnl.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class Item_click_rocket implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (rocketPnl.getMode() == "right") {
                ItemPanel clicked = null;
                if (e.getX() >= 100 && e.getX() <= 145 && e.getY() >= 180 && e.getY() <= 225) {
                    clicked = rocketPnl.getItem(0);
                } else if (e.getX() >= 150 && e.getX() <= 195 && e.getY() >= 180 && e.getY() <= 225) {
                    clicked = rocketPnl.getItem(1);
                }
                rocketPnl.removeItem(clicked);
                switch (clicked.getFilename()) {
                    case "resources/boy.png":
                        human1.setBounds(0, 365, 50, 50);
                        human1.setVisible(true);
                        break;
                    case "resources/girl.png":
                        human2.setBounds(50, 365, 50, 50);
                        human2.setVisible(true);
                        break;
                    case "resources/lion.png":
                        lion.setBounds(100, 365, 50, 50);
                        lion.setVisible(true);
                        break;
                    case "resources/cow.png":
                        cow.setBounds(150, 365, 50, 50);
                        cow.setVisible(true);
                        break;
                    case "resources/rice.png":
                        rice.setBounds(200, 365, 50, 50);
                        rice.setVisible(true);
                        break;
                }
                rocketPnl.repaint();
            } else if (rocketPnl.getMode() == "left") {
                ItemPanel clicked = null;
                if (e.getX() >= 515 && e.getX() <= 555 && e.getY() >= 180 && e.getY() <= 225) {
                    clicked = rocketPnl.getItem(0);
                } else if (e.getX() >= 565 && e.getX() <= 610 && e.getY() >= 180 && e.getY() <= 225) {
                    clicked = rocketPnl.getItem(1);
                }
                rocketPnl.removeItem(clicked);
                switch (clicked.getFilename()) {
                    case "resources/boy.png":
                        human1.setBounds(440, 365, 50, 50);
                        human1.setVisible(true);
                        break;
                    case "resources/girl.png":
                        human2.setBounds(490, 365, 50, 50);
                        human2.setVisible(true);
                        break;
                    case "resources/lion.png":
                        lion.setBounds(540, 365, 50, 50);
                        lion.setVisible(true);
                        break;
                    case "resources/cow.png":
                        cow.setBounds(590, 365, 50, 50);
                        cow.setVisible(true);
                        break;
                    case "resources/rice.png":
                        rice.setBounds(640, 365, 50, 50);
                        rice.setVisible(true);
                        break;
                }
                rocketPnl.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class helpBtn_click implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (JOptionPane.showConfirmDialog (null, "Are you sure you wanna see the solution?","Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // solution here
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            try {
                helpBtn.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/helpHover.png"))));
            } catch (IOException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            try {
                helpBtn.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/help.png"))));
            } catch (IOException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class infoBtn_click implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(mainPane, "Put rules here");
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            try {
                infoBtn.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/infoHover.png"))));
            } catch (IOException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            try {
                infoBtn.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/info.png"))));
            } catch (IOException ex) {
                Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
