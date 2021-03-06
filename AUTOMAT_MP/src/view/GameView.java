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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Node;

/**
 *
 * @author ianona
 */
public class GameView extends JFrame {

    private GameController controller;
    private JLabel bgImage, rightPnl, leftPnl, RBtn, LBtn, helpBtn, infoBtn, leftPnl2, rightPnl2;
    private JLayeredPane mainPane;
    private RocketPanel rocketPnl;
    private ItemPanel human1, human2, lion, cow, rice;
    private List<List<Node>> solutions;
    private JList solutionsList;
    private JTextArea solutionsFeed;

    private static final Rectangle leftSide = new Rectangle(30, 120, 255, 153);
    private static final Rectangle rightSide = new Rectangle(420, 120, 255, 153);

    public GameView(GameController gc) {
        this.setSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("AUTOMAT MP");
        controller = gc;
        
        mainPane = new JLayeredPane();
        mainPane.setPreferredSize(new Dimension(700, 500));
        
        this.reset();
    }

    public void reset(){
        mainPane.removeAll();
        
        try {
            bgImage = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/bg.png"))));
            leftPnl = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/panel.png"))));
            rightPnl = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/panel2.png"))));
            leftPnl2 = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/grayPnl.png"))));
            rightPnl2 = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/grayPnl2.png"))));
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
        leftPnl2.setBounds(0, 350, 297, 77);
        rightPnl2.setBounds(410, 350, 297, 77);
        leftPnl2.setVisible(false);
        rightPnl2.setVisible(true);
        leftPnl2.addMouseListener(new grayPnl_click());
        rightPnl2.addMouseListener(new grayPnl_click());
        mainPane.add(bgImage, new Integer(0));
        mainPane.add(leftPnl, new Integer(100));
        mainPane.add(rightPnl, new Integer(100));
        mainPane.add(leftPnl2, new Integer(200));
        mainPane.add(rightPnl2, new Integer(200));

        rocketPnl = new RocketPanel(leftSide, this);
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

        human1 = new ItemPanel("human1", "resources/boy.png");
        human2 = new ItemPanel("human2", "resources/girl.png");
        lion = new ItemPanel("lion", "resources/lion.png");
        cow = new ItemPanel("cow", "resources/cow.png");
        rice = new ItemPanel("rice", "resources/rice.png");

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
    
    public void emptyRocket() {
        if (rocketPnl.getMode() == "right") {
            for (int i = rocketPnl.getItems().size() - 1; i > -1; i--) {
                ItemPanel cur = rocketPnl.getItem(i);
                rocketPnl.removeItem(cur);
                switch (cur.getFilename()) {
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
            }
        } else if (rocketPnl.getMode() == "left") {
            for (int i = rocketPnl.getItems().size() - 1; i > -1; i--) {
                ItemPanel cur = rocketPnl.getItem(i);
                rocketPnl.removeItem(cur);
                switch (cur.getFilename()) {
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
    }

    public void updateController() {
        String move = "";
        if (rocketPnl.getMode() == "right") {
            move = "left";
        } else {
            move = "right";
        }

        if (rocketPnl.getItems().size() == 1) {
            controller.moveShip(rocketPnl.getItem(0).getType(), move);
        } else if (rocketPnl.getItems().size() == 2) {
            controller.moveShip(rocketPnl.getItem(0).getType(), rocketPnl.getItem(1).getType(), move);
        }

        controller.updateMachine(move);
        
        if (!controller.checkPlanets(move)) {
            displayMessage("Game Over! Program terminating...");
        }
        
        if (controller.checkWin()) {
            displayMessage("You Win! You solved the puzzle in " + controller.getMoveCount() + " moves!");
        }
    }

    public void displayMessage(String message){
        this.emptyRocket();
        
        String[] options = {"Retry?","Quit"};
        int choice = JOptionPane.showOptionDialog(null, 
        message, 
        "", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.INFORMATION_MESSAGE, 
        null, 
        options, 
        options[0]);
        
        if(choice == JOptionPane.YES_OPTION){
            System.out.println("Clicked retry");
            controller.reset();
            controller.clearSolutions();
            this.reset();
        }
        else{
            System.exit(0);
        }
    }
    
    class RBtn_click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            rocketPnl.animate(rightSide);
            RBtn.setVisible(false);
            LBtn.setVisible(true);
            rightPnl2.setVisible(false);
            leftPnl2.setVisible(true);
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
            rightPnl2.setVisible(true);
            leftPnl2.setVisible(false);
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
            try {
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
            } catch (Exception ex) {
                System.out.println("don't click here!");
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
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you wanna see the solution?", "Warning", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                // ask controller for solutions
                solutions = controller.getShortestPaths();
                
                // add solutions to a JList
                solutionsList = new JList(solutions.toArray());
                solutionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                solutionsList.setLayoutOrientation(JList.VERTICAL);
                solutionsList.getSelectionModel().addListSelectionListener(new solution_onChange());
                
                // create text area to put instructions in
                solutionsFeed = new JTextArea(5,6);
                solutionsFeed.setEditable(false);
                
                // supplementary choochoo for new dialog
                final JComponent[] inputs = new JComponent[]{
                    new JLabel("There are " + solutions.size() + " shortest paths found."),
                    solutionsList,
                    solutionsFeed};
                Object[] options = {"Got it!", "Got it! (but keep the solution please)"};
                
                UIManager.put("OptionPane.minimumSize",new Dimension(270, 270));
                
                // show new dialog for solutions
                int n = JOptionPane.showOptionDialog(null,
                        inputs,
                        "Solution",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
                
                // clear solution if user chooses to clear
                if (n == JOptionPane.YES_OPTION){
                    controller.clearSolutions();
                }
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
            JOptionPane.showMessageDialog(mainPane,
                    "You are a scientist, and you want to transfer five things to Mars to leave the dying earth:\n"
                    + "two(2) Humans, one(1) Lion, one(1) Cow, and one(1) Bag of grain.\n\n"
                    + "When the scientist isn't present:\n"
                    + "The lion will eat the cow,\n"
                    + "The cow will eat the grain,\n"
                    + "and the humans will kill the lion or the cow.\n\n"
                    + "Your spaceship can only hold two passengers, and only you can operate the rocket ship.\n"
                    + "Find a way to transfer all the items to Mars without anything dying or being eaten.");
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

    class grayPnl_click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
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

    // action listener for when user selects different solution
    class solution_onChange implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //System.out.println("SELECTING: " + solutionsList.getSelectedValue());
            List<Node> curSolution = (List<Node>) solutionsList.getSelectedValue();
            
            String curText = curSolution.toString();
            
            if (curText.equalsIgnoreCase("[n1, n2, n3, n7, n8, n11, n12, n13]"))
                solutionsFeed.setText("Step 1: Send Lion and Cow to Mars\n "
                        + "Step 2: Send Cow to Earth \n "
                        + "Step 3: Send Cow and Rice to Mars \n"
                        + "Step 4: Send Cow to Earth \n"
                        + "Step 5: Send Both Humans to Mars \n"
                        + "Step 6: Send Lion to Earth \n"
                        + "Step 7: Send Lion and Cow to Mars"
                        );
            else if(curText.equalsIgnoreCase("[n1, n2, n6, n7, n8, n11, n12, n13]"))
                solutionsFeed.setText("Step 1: Send Lion and Cow to Mars\n "
                        + "Step 2: Send Lion to Earth \n "
                        + "Step 3: Send Lion and Rice to Mars \n"
                        + "Step 4: Send Cow to Earth \n"
                        + "Step 5: Send Both Humans to Mars \n"
                        + "Step 6: Send Lion to Earth \n"
                        + "Step 7: Send Lion and Cow to Mars"
                        );
            else if(curText.equalsIgnoreCase("[n1, n2, n6, n15, n17, n14, n12, n13]"))
                solutionsFeed.setText("Step 1: Send Lion and Cow to Mars\n "
                        + "Step 2: Send Lion to Earth \n "
                        + "Step 3: Send Both Humans to Mars \n"
                        + "Step 4: Send Cow to Earth \n"
                        + "Step 5: Send Cow and Rice to Mars \n"
                        + "Step 6: Send Cow to Earth \n"
                        + "Step 7: Send Lion and Cow to Mars"
                        );
            else if(curText.equalsIgnoreCase("[n1, n2, n6, n15, n17, n11, n12, n13]"))
                solutionsFeed.setText("Step 1: Send Lion and Cow to Mars\n "
                        + "Step 2: Send Lion to Earth \n "
                        + "Step 3: Send Both Humans to Mars \n"
                        + "Step 4: Send Cow to Earth \n"
                        + "Step 5: Send Lion and Rice to Mars \n"
                        + "Step 6: Send Lion to Earth \n"
                        + "Step 7: Send Lion and Cow to Mars"
                        );
            else
                solutionsFeed.setText("");

            controller.updateMachine(curSolution);
        }
    }
}
