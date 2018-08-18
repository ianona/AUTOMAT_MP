/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianona
 */
public class Node {
    private String nodeName;
    private List<Node> adjacentNodes;
    
    public Node(String nodeName) {
        this.nodeName = nodeName;
        adjacentNodes = new ArrayList<>();
    }
    
    public void addAdjacent(Node n){
        adjacentNodes.add(n);
    }
    
    public void addAdjacent(Node[] nodes){
        for(Node n:nodes)
            adjacentNodes.add(n);
    }
}
