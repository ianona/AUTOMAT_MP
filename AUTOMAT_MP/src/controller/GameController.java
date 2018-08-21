/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import model.GraphSingleton;
import model.GraphSolution;
import model.Node;
import view.MachineView;

/**
 *
 * @author ianona
 */
public class GameController {
    private String[] earth;
    private String[] mars;
    private String[] ship;
    
    private MachineView mv;
    private GraphSolution gs;
    
    private int moveCount;
    
    public GameController(){
        reset();
    }
    
    public void reset(){
        earth = new String[]{"human1","human2","cow","rice","lion"};
        mars = new String[]{"","","","",""};
        ship = new String[]{"",""};
        moveCount = 1;
    }
    
    public void incrementMove(){
        moveCount++;
    }
    
    public int getMoveCount(){
        return moveCount;
    }
    
    // updates machine per step made
    public void updateMachine(String direction){
        String left = "";
        for (int i = 0; i < earth.length; i++)
            if (earth[i] != "")
                left += earth[i].toUpperCase().charAt(0);
        
        String right = "";
        for (int i = 0; i < mars.length; i++)
            if (mars[i] != "")
                right += mars[i].toUpperCase().charAt(0);
        
        mv.updateMachine(left, right, direction);
        incrementMove();
    }
    
    // update machine with the solution nodes
    public void updateMachine(List<Node> nodes){
        mv.updateMachine(nodes);
    }
    
    // clear machine view of any solution
    public void clearSolutions(){
        mv.clearSol();
    }
    
    public void attach(MachineView mv){
        this.mv = mv;
    }
    
    public void attach(GraphSolution gs){
        this.gs = gs;
    }
    
    // overloaded method to move passengers
    // Moves the ship and begins the transfer of passengers
    public void moveShip(String pass1, String pass2, String direction){
        if(direction.equalsIgnoreCase("right")){
            if (!pass1.equalsIgnoreCase("")){
                if(isIn(earth,pass1)){
                    removeFrom(earth,pass1);
                    addTo(mars,pass1);
                }
                else{
                    System.out.println("pass1 not in earth");
                }
            }
            if(!pass2.equalsIgnoreCase("")){
                if(isIn(earth,pass2)){
                    removeFrom(earth,pass2);
                    addTo(mars,pass2);
                }
                else{
                    System.out.println("pass2 not in earth");
                }
            }
        }
        else{
            if (!pass1.equalsIgnoreCase("")){
                if(isIn(mars,pass1)){
                    removeFrom(mars,pass1);
                    addTo(earth,pass1);
                }
                else{
                    System.out.println("pass1 not in mars");
                }
            }
            if(!pass2.equalsIgnoreCase("")){
                if(isIn(mars,pass2)){
                    removeFrom(mars,pass2);
                    addTo(earth,pass2);
                }
                else{
                    System.out.println("pass2 not in mars");
                }
            }
        }
        
        System.out.println("EARTH: " + Arrays.toString(earth));
        System.out.println("MARS: " + Arrays.toString(mars));
    }
    
    // Moves the ship and begins the transfer of passengers
    public void moveShip(String pass1, String direction){
        if(direction.equalsIgnoreCase("right")){
            if (!pass1.equalsIgnoreCase("")){
                if(isIn(earth,pass1)){
                    removeFrom(earth,pass1);
                    addTo(mars,pass1);
                }
                else{
                    System.out.println("pass1 not in earth");
                }
            }
        }
        else{
            if (!pass1.equalsIgnoreCase("")){
                if(isIn(mars,pass1)){
                    removeFrom(mars,pass1);
                    addTo(earth,pass1);
                }
                else{
                    System.out.println("pass1 not in mars");
                }
            }
        }
        
        System.out.println("EARTH: " + Arrays.toString(earth));
        System.out.println("MARS: " + Arrays.toString(mars));
    }
    
    // Checks if the planet the spaceship is leaving is valid
    public Boolean checkPlanets(String move){
        if(move.equalsIgnoreCase("right"))
            return checkEarth();
        else
            return checkMars();
    }
    
    // Checks if the current condition of earth is allowed
    public Boolean checkEarth(){
        Boolean human1 = isIn(earth,"human1");
        Boolean human2 = isIn(earth,"human2");
        Boolean rice = isIn(earth,"rice");
        Boolean lion = isIn(earth,"lion");
        Boolean cow = isIn(earth,"cow");
        
        if(human1 && lion || human2 && lion)
            return false;
        
        if(human1 && cow || human2 && cow)
            return false;
        
        if(lion && cow)
            return false;
        
        if (cow && rice)
            return false;
        
        return true;
    }
    
    // Checks if the current condition of mars is allowed
    public Boolean checkMars(){
        Boolean human1 = isIn(mars,"human1");
        Boolean human2 = isIn(mars,"human2");
        Boolean rice = isIn(mars,"rice");
        Boolean lion = isIn(mars,"lion");
        Boolean cow = isIn(mars,"cow");
        
        if(human1 && lion || human2 && lion)
            return false;
        
        if(human1 && cow || human2 && cow)
            return false;
        
        if(lion && cow)
            return false;
        
        if (cow && rice)
            return false;
        
        return true;
    }
    
    public boolean checkWin(){
        Boolean human1 = isIn(mars,"human1");
        Boolean human2 = isIn(mars,"human2");
        Boolean rice = isIn(mars,"rice");
        Boolean lion = isIn(mars,"lion");
        Boolean cow = isIn(mars,"cow");
        
        if (human1 && human2 && rice && lion && cow)
            return true;
        else
            return false;
    }
    
    // Removes from given array the given value
    public void removeFrom(String[] array, String value){
        for(int i = 0; i < array.length; i++)
            if(array[i].equalsIgnoreCase(value)){
                array[i] = "";
                break;
            }
    }
    
    // Adds to given array the given value
    public void addTo(String[] array, String value){
        for(int i = 0; i < array.length; i++)
            if(array[i].equalsIgnoreCase("")){
                array[i] = value;
                break;
            }
        
    }
    
    // Checks if the given value is inside the given array
    public Boolean isIn(String[] array,String value){
        for(String element: array)
            if(element.equalsIgnoreCase(value))
                return true;
        return false;
    }
    
    public List<List<Node>> getShortestPaths(){
        // returns all shortest paths from initial state to final state
        return gs.findAllShortestPaths(GraphSingleton.n1, GraphSingleton.n13);
    }
}
