/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.GameView;
import view.MachineView;

/**
 *
 * @author ianona
 */
public class AUTOMAT_MP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameController gc = new GameController();
        GameView gv = new GameView(gc);
        MachineView mv = new MachineView(gc);
    }
    
}
