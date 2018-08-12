/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;

/**
 *
 * @author ianona
 */
public class GameController {
    private String[] earth;
    private String[] mars;
    private String move;
    private String[] ship;
    
    GameController(){
        earth = new String[]{"human1","human2","cow","grain","lion"};
        mars = new String[]{"","","","",""};
        ship = new String[]{"",""};
        move = "Right";
        gameTime();
    }
    
    public void gameTime(){
        // while(true){
            //ask for the user input
            
            //execute
            moveShip("human1","human2",move);
            System.out.println("moved ship");
            //check after ship has left
            if(!checkPlanets(move))
                System.out.println("Game Over");
            System.out.println("checked planet");
            //change move
            if(move.equalsIgnoreCase("right"))
                move = "Left";
            else
                move = "Right";
            System.out.println("changed direction");
        //}
    }
    
    // Moves the ship and begins the transfer of passengers
    public void moveShip(String pass1, String pass2, String direction){
        if(direction.equalsIgnoreCase("right")){
            if (!pass1.equalsIgnoreCase("")){
                if(isIn(earth,pass1)){
                    removeFrom(earth,pass1);
                    addTo(mars,pass1);
                    System.out.println(Arrays.toString(earth));
                    System.out.println(Arrays.toString(mars));
                }
                else{
                    System.out.println("pass1 not in earth");
                }
            }
            if(!pass2.equalsIgnoreCase("")){
                if(isIn(earth,pass2)){
                    removeFrom(earth,pass2);
                    addTo(mars,pass2);
                    System.out.println(Arrays.toString(earth));
                    System.out.println(Arrays.toString(mars));
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
                    System.out.println(Arrays.toString(earth));
                    System.out.println(Arrays.toString(mars));
                }
                else{
                    System.out.println("pass1 not in mars");
                }
            }
            if(!pass2.equalsIgnoreCase("")){
                if(isIn(mars,pass2)){
                    removeFrom(mars,pass2);
                    addTo(earth,pass2);
                    System.out.println(Arrays.toString(earth));
                    System.out.println(Arrays.toString(mars));
                }
                else{
                    System.out.println("pass2 not in mars");
                }
            }
        }
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
        Boolean grain = isIn(earth,"grain");
        Boolean lion = isIn(earth,"lion");
        Boolean cow = isIn(earth,"cow");
        
        if(human1 && lion || human2 && lion)
            return false;
        
        if(human1 && cow || human2 && cow)
            return false;
        
        if(lion && cow)
            return false;
        
        if (cow && grain)
            return false;
        
        return true;
    }
    
    // Checks if the current condition of mars is allowed
    public Boolean checkMars(){
        Boolean human1 = isIn(mars,"human1");
        Boolean human2 = isIn(mars,"human2");
        Boolean grain = isIn(mars,"grain");
        Boolean lion = isIn(mars,"lion");
        Boolean cow = isIn(mars,"cow");
        
        if(human1 && lion || human2 && lion)
            return false;
        
        if(human1 && cow || human2 && cow)
            return false;
        
        if(lion && cow)
            return false;
        
        if (cow && grain)
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
