/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author ianona
 */
public class MachineView extends JFrame{
    private GameController controller;
    public MachineView(GameController gc){
        this.setSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("State Machine");
        controller = gc;
        
        this.setLocation(700, 0);
        this.setVisible(true);
        this.setResizable(false);
    }
    
}
