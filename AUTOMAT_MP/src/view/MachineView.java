/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import model.Node;

/**
 *
 * @author ianona
 */
public class MachineView extends JFrame {

    private GameController controller;
    private static int size = 75;
    private List<StatePanel> states;
    private JLabel arrows;
    private JLayeredPane mainPane;
    private StatePanel initial;
    
    public MachineView(GameController gc) {
        this.setSize(new Dimension(900, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("State Machine");
        controller = gc;
        states = new ArrayList<>();

        try {
            arrows = new JLabel(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/arrows.png"))));
        } catch (IOException e) {
            System.out.println("file not found");
        }

        mainPane = new JLayeredPane();
        mainPane.setPreferredSize(new Dimension(900, 600));
        
        this.reset();
    }

    public void reset(){
        System.out.println("ew");
        this.remove(mainPane);
        mainPane.removeAll();
        arrows.setBounds(0, 0, 900, 600);
        mainPane.add(arrows, new Integer(0));

        this.initMachine();
        states.get(0).setCur(true);
        this.initial.repaint();
        this.add(mainPane);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocation(700, 0);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public void updateMachine(String left, String right, String direction) {
        for (StatePanel sp : states) {
            if (sp.getStateName().equalsIgnoreCase(direction)
                    && isAnagram(right, sp.getRight())
                    && isAnagram(left, sp.getLeft())) {
                sp.setCur(true);
            } else {
                sp.setCur(false);
                //sp.setSol(false);
            }
            sp.repaint();
        }
    }
    
    public void clearSol(){
        for (StatePanel sp : states) {
            sp.setSol(false);
            sp.repaint();
        }
    }

    public void updateMachine(List<Node> nodes) {
        for (StatePanel sp : states) {
            if (sp.getSol())
                sp.setSol(false);
        }
        for (StatePanel sp : states) {
            for (Node node : nodes) {
                if (isAnagram(node.getRight(), sp.getRight()) && 
                    isAnagram(node.getLeft(), sp.getLeft())) {
                    sp.setSol(true);
                }
            }
            sp.repaint();
        }

    }

    public void initMachine() {
        // HARDCODE THE STATE MACHINE GUI

        // FIRST VERTICAL COLUMN OF STATES
        StatePanel sp1 = new StatePanel("LEFT", "HHCLR", "");
        sp1.setBounds(20, 40, size, size);
        sp1.setCur(true);
        initial = sp1;
        this.add(sp1);
        states.add(sp1);

        StatePanel sp2 = new StatePanel("RIGHT", "HHR", "LC");
        states.add(sp2);
        sp2.setBounds(20, (states.size() - 1) * 110 + 40, size, size);
        this.add(sp2);

        StatePanel sp3 = new StatePanel("LEFT", "HHCR", "L");
        states.add(sp3);
        sp3.setBounds(20, (states.size() - 1) * 110 + 40, size, size);
        this.add(sp3);

        StatePanel sp4 = new StatePanel("RIGHT", "HR", "HCL");
        states.add(sp4);
        sp4.setBounds(20, (states.size() - 1) * 110 + 40, size, size);
        this.add(sp4);

        StatePanel sp5 = new StatePanel("LEFT", "HRCL", "H");
        states.add(sp5);
        sp5.setBounds(20, (states.size() - 1) * 110 + 40, size, size);
        this.add(sp5);

        // 6 STATES EXTENDING TO THE RIGHT
        StatePanel sp6 = new StatePanel("LEFT", "HHLR", "C");
        states.add(sp6);
        sp6.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp6);

        StatePanel sp7 = new StatePanel("RIGHT", "HH", "LRC");
        states.add(sp7);
        sp7.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp7);

        StatePanel sp8 = new StatePanel("LEFT", "CHH", "LR");
        states.add(sp8);
        sp8.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp8);

        StatePanel sp9 = new StatePanel("RIGHT", "H", "CHLR");
        states.add(sp9);
        sp9.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp9);

        StatePanel sp10 = new StatePanel("LEFT", "LCH", "HR");
        states.add(sp10);
        sp10.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp10);

        StatePanel sp11 = new StatePanel("RIGHT", "C", "LHHR");
        states.add(sp11);
        sp11.setBounds((states.size() - 5) * 110 + 20, 150, size, size);
        this.add(sp11);

        // 3 states in the right side
        StatePanel sp12 = new StatePanel("LEFT", "CL", "HHR");
        states.add(sp12);
        sp12.setBounds((states.size() - 5) * 110 + 20, 260, size, size);
        this.add(sp12);

        StatePanel sp13 = new StatePanel("RIGHT", "", "HHCLR");
        states.add(sp13);
        sp13.setBounds(790, 370, size, size);
        sp13.setFinal(true);
        this.add(sp13);

        StatePanel sp14 = new StatePanel("RIGHT", "L", "CHHR");
        states.add(sp14);
        sp14.setBounds(680, 370, size, size);
        this.add(sp14);

        // 2 states in the center
        StatePanel sp15 = new StatePanel("RIGHT", "LR", "CHH");
        states.add(sp15);
        sp15.setBounds(210, 340, size, size);
        this.add(sp15);

        StatePanel sp16 = new StatePanel("LEFT", "CLHH", "R");
        states.add(sp16);
        sp16.setBounds(320, 340, size, size);
        this.add(sp16);

        // bottom 2 states
        StatePanel sp17 = new StatePanel("LEFT", "CLR", "HH");
        states.add(sp17);
        sp17.setBounds(210, 480, size, size);
        this.add(sp17);

        StatePanel sp18 = new StatePanel("RIGHT", "R", "CLHH");
        states.add(sp18);
        sp18.setBounds(430, 480, size, size);
        this.add(sp18);
    }

    public boolean isAnagram(String s1, String s2) {
        if (s1.length() == s2.length()) {
            char[] s1AsChar = s1.toCharArray();
            char[] s2AsChar = s2.toCharArray();
            Arrays.sort(s1AsChar);
            Arrays.sort(s2AsChar);
            return Arrays.equals(s1AsChar, s2AsChar);
        }
        return false;
    }
}
