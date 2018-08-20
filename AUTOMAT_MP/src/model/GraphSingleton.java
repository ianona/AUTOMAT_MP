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

// singleton class to store graph, nodes, and initializations
public class GraphSingleton {
    private static final GraphSingleton instance = new GraphSingleton();
    public static HashMap<Node, List<Node>> graph;
    public static Node n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18;
    
    private GraphSingleton(){
        graph = new HashMap<>();
        this.initialize();
    }
    
    public final void initialize() {
        // node n1 corresponds to state panel sp1 and so on and so forth
        n1 = new Node("HHCLR", "", 1);
        n2 = new Node("HHR", "LC", 2);
        n3 = new Node("HHCR", "L", 3);
        n4 = new Node("HR", "HCL", 4);
        n5 = new Node("HRCL", "H", 5);

        n6 = new Node("HHLR", "C", 6);
        n7 = new Node("HH", "LRC", 7);
        n8 = new Node("CHH", "LR", 8);
        n9 = new Node("H", "CHLR", 9);
        n10 = new Node("LCH", "HR", 10);
        n11 = new Node("C", "LHHR", 11);

        n12 = new Node("CL", "HHR", 12);
        n13 = new Node("", "HHCLR", 13);
        n14 = new Node("L", "CHHR", 14);

        n15 = new Node("LR", "CHH", 15);
        n16 = new Node("CLHH", "R", 16);

        n17 = new Node("CLR", "HH", 17);
        n18 = new Node("R", "CLHH", 18);

        graph.put(n1, Arrays.asList(new Node[]{n2}));
        graph.put(n2, Arrays.asList(new Node[]{n1, n3, n6}));
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

        graph.put(n17, Arrays.asList(new Node[]{n15, n18, n14, n11}));
        graph.put(n18, Arrays.asList(new Node[]{n17}));
    }

    public static GraphSingleton getInstance(){
        return instance;
    }
}
