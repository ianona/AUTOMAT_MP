/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author ianona
 */
public class MachineView extends JFrame{
    private GameController controller;
    private static int size = 71;
    
    public MachineView(GameController gc){
        this.setSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("State Machine");
        this.setLayout(null);
        controller = gc;
        
        this.initMachine();
        
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocation(700, 0);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public void initMachine(){
        StatePanel sp1 = new StatePanel("SAMPLE1");
        sp1.setBounds(0, 0, size,size);
        sp1.setCur(true);
        this.add(sp1);
        
        StatePanel sp2 = new StatePanel("SAMPLE2");
        sp2.setBounds((size*1)+40, 0, size,size);
        this.add(sp2);
    }
    
}
