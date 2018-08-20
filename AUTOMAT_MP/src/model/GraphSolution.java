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

// class to store DFS algo
public class GraphSolution {
    
    // finds one shortest path
    public final List<Node> findShortestPath(Node start, Node end, List<Node> path) {
        List<Node> path2 = new ArrayList<>();
        for (Node node:path)
            path2.add(node);
        path2.add(start);
        
        if (start.getNode().equalsIgnoreCase(end.getNode())) {
            return path2;
        }
        
        List<Node> shortestPath = null;
        for (Node n : GraphSingleton.graph.get(start)) {
            if (!path2.contains(n)) {
                List<Node> newPath = findShortestPath(n, end, path2);
                if (newPath != null) {
                    if (shortestPath == null)
                        shortestPath = newPath;
                    else if(newPath.size() < shortestPath.size())
                        shortestPath = newPath;
                }
            }
        }
        return shortestPath;
    }
    
    // finds all paths and keeps only the shortest of the paths
    public final List<List<Node>> findAllShortestPaths(Node start, Node end) {
        List<List<Node>> shortestPaths = findAllPaths(start, end, new ArrayList<>());
        int small = -1;
        for (List<Node> nodes:shortestPaths){
            if (small == -1 || nodes.size() < small)
                small = nodes.size();
        }
        for (int i = shortestPaths.size()-1; i > -1; i--)
            if (shortestPaths.get(i).size() != small)
                shortestPaths.remove(i);
        return shortestPaths;
    }
            
    // find one path (any path)
    public final List<Node> findPath(Node start, Node end, List<Node> path) {
        List<Node> nullPath = null;
        List<Node> path2 = new ArrayList<>();
        for (Node node:path)
            path2.add(node);
        path2.add(start);

        if (start.getNode().equalsIgnoreCase(end.getNode())) {
            return path2;
        }

        for (Node n : GraphSingleton.graph.get(start)) {
            if (!path2.contains(n)) {
                List<Node> newPath = findPath(n, end, path2);
                if (newPath != null) {
                    return newPath;
                }
            }
        }
        return nullPath;
    }

    // find all possible paths
    public final List<List<Node>> findAllPaths(Node start, Node end, List<Node> path) {
        List<Node> path2 = new ArrayList<>();
        for (Node node:path)
            path2.add(node);
        path2.add(start);

        if (start.getNode().equalsIgnoreCase(end.getNode())) {
            List<List<Node>> p = new ArrayList<>();
            p.add(path2);
            return p;
        }

        List<List<Node>> paths = new ArrayList<>();
        for (Node n : GraphSingleton.graph.get(start)) {
            if (!path2.contains(n)) {
                for (List<Node> newPath : findAllPaths(n, end, path2)) {
                    paths.add(newPath);
                }
            }
        }
        return paths;
    }
}
