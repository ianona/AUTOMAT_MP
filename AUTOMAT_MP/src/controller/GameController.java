/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import view.MachineView;

/**
 *
 * @author ianona
 */
public class GameController {
    private String[] earth;
    private String[] mars;
    private String move;
    private String[] ship;
    
    private MachineView mv;
    
    GameController(){
        earth = new String[]{"human1","human2","cow","rice","lion"};
        mars = new String[]{"","","","",""};
        ship = new String[]{"",""};
    }
    
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
    }
    
    public void attach(MachineView mv){
        this.mv = mv;
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
}
