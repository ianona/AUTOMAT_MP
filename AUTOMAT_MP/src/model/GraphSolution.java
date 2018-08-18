/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ianona
 */
public class GraphSolution {
    HashMap<Node, List<Node>> graph;
    
    public GraphSolution(){
        graph = new HashMap<Node, List<Node>>();
        this.initialize();
    }
    
    public void initialize(){
        // node n1 corresponds to state panel sp1 and so on and so forth
        Node n1 = new Node("HHCLR/");
        Node n2 = new Node("HHR/LC");
        Node n3 = new Node("HHCR/L");
        Node n4 = new Node("HR/HCL");
        Node n5 = new Node("HRCL/H");
        
        Node n6 = new Node("HHLR/C");
        Node n7 = new Node("HH/LRC");
        Node n8 = new Node("CHH/LR");
        Node n9 = new Node("H/CHLR");
        Node n10 = new Node("LCH/HR");
        Node n11 = new Node("C/LHHR");
        
        Node n12 = new Node("CL/HHR");
        Node n13 = new Node("/HHCLR");
        Node n14 = new Node("L/CHHR");
        
        Node n15 = new Node("LR/CHH");
        Node n16 = new Node("CLHH/R");
        
        Node n17 = new Node("CLR/HH");
        Node n18 = new Node("R/CLHH");
        
        graph.put(n1, Arrays.asList(new Node[]{n2}));
        graph.put(n2, Arrays.asList(new Node[]{n3, n6}));
        graph.put(n3, Arrays.asList(new Node[]{n2, n4, n7}));
        graph.put(n4, Arrays.asList(new Node[]{n3, n5, n6}));
        graph.put(n5, Arrays.asList(new Node[]{n4, n15}));
        
        graph.put(n6, Arrays.asList(new Node[]{n2, n7, n4, n15}));
        graph.put(n7, Arrays.asList(new Node[]{n6, n8, n3, n16}));
        graph.put(n8, Arrays.asList(new Node[]{n7, n9, n11}));
        graph.put(n9, Arrays.asList(new Node[]{n8, n10}));
        graph.put(n10, Arrays.asList(new Node[]{n9, n11, n14}));
        graph.put(n11, Arrays.asList(new Node[]{n10, n12, n8, n17}));
        
        graph.put(n12, Arrays.asList(new Node[]{n11, n13, n14}));
        graph.put(n13, Arrays.asList(new Node[]{n12}));
        graph.put(n14, Arrays.asList(new Node[]{n12, n10, n17}));
        
        graph.put(n15, Arrays.asList(new Node[]{n6, n5, n17}));
        graph.put(n16, Arrays.asList(new Node[]{n7}));
        
        graph.put(n17, Arrays.asList(new Node[]{n15, n11, n14, n18}));
        graph.put(n18, Arrays.asList(new Node[]{n17}));
        /*
        n1.addAdjacent(new Node[]{n2});
        n2.addAdjacent(new Node[]{n3, n6});
        n3.addAdjacent(new Node[]{n2, n4, n7});
        n4.addAdjacent(new Node[]{n3, n5, n6});
        n5.addAdjacent(new Node[]{n4, n15});
        
        n6.addAdjacent(new Node[]{n2, n7, n4, n15});
        n7.addAdjacent(new Node[]{n6, n8, n3, n16});
        n8.addAdjacent(new Node[]{n7, n9, n11});
        n9.addAdjacent(new Node[]{n8, n10});
        n10.addAdjacent(new Node[]{n9, n11, n14});
        n11.addAdjacent(new Node[]{n10, n12, n8, n17});
        
        n12.addAdjacent(new Node[]{n11, n13, n14});
        n13.addAdjacent(new Node[]{n12});
        n14.addAdjacent(new Node[]{n12, n10, n17});
        
        n15.addAdjacent(new Node[]{n6, n5, n17});
        n16.addAdjacent(new Node[]{n7});
        
        n17.addAdjacent(new Node[]{n15, n11, n14, n18});
        n18.addAdjacent(new Node[]{n17});
        */
    }
}
