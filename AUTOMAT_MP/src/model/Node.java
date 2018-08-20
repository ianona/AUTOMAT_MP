/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ianona
 */
public class Node {

    private String nodeName, left, right;
    private int nodeNumber;

    public Node(String left, String right, int nodeNumber) {
        this.left = left;
        this.right = right;
        this.nodeName = left+"/"+right;
        this.nodeNumber = nodeNumber;
    }

    public String getNode() {
        return nodeName;
    }
    
    public String toString() {
        return "n"+nodeNumber;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}
