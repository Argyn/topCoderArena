package com.topcoder.rauanm.exercises.abcpath;

import java.util.*;

/**
 * Created by Argyn on 04/10/2015.
 */
public class ABCPath {

    private Graph graph;
    private int lengthResult;

    public ABCPath(String[] args) {
        constructGraph(args);
        lengthResult = 0;
    }

    public void constructGraph(String[] args) {
        boolean aExists = false;

        for(String nodes : args) {
            if(nodes.contains("A")) {
                aExists = true;
                break;
            }
        }

        if(aExists) {
            graph = new Graph();
            int nodeLength = args[0].length();

            // merge into one array
            char[] resultChars = new char[nodeLength*args.length];
            int indexSoFar = 0;
            for(String nodes : args) {
                char[] nodesChars = nodes.toCharArray();
                for(char c : nodesChars) {
                    resultChars[indexSoFar] = c;
                }
            }

            Node[][] nodesGrid = new Node[args.length][args[0].length()];
            List<Node> startingPoints = new ArrayList<>();

            for(int i=0; i<args.length; i++) {
                for(int j=0; j<args[0].length(); j++) {
                    char c = args[i].charAt(j);
                    Node node = new Node(c);
                    if(c=='A')
                        startingPoints.add(node);

                    nodesGrid[i][j] = node;
                    System.out.format("%c  ", node.getValue());
                    graph.addNode(node);
                }
                System.out.println();
            }


            for(int i=0; i<args.length; i++) {
                for(int j=0; j<args[0].length(); j++) {
                    // link node to the right
                    if(j+1<nodeLength) {
                        graph.addEdge(nodesGrid[i][j], nodesGrid[i][j+1]);
                    }

                    // link bottom node
                    if(i+1<nodeLength) {
                        graph.addEdge(nodesGrid[i][j], nodesGrid[i+1][j]);
                    }

                    // link left and right diagonal
                    if(i!=args.length-1) {
                        if(j!=nodeLength-1) {
                            // link only bottom right
                            graph.addEdge(nodesGrid[i][j], nodesGrid[i+1][j+1]);
                        }

                        if(j!=0) {
                            // link only bottom left
                            graph.addEdge(nodesGrid[i][j], nodesGrid[i+1][j-1]);
                        }
                    }
                }
            }


            printGraph(graph);
        }
    }

    public void printGraph(Graph graph) {
        for(Edge e : graph.getEdges()) {
            System.out.println(e);
        }
    }

    public void findLongestPath(Graph graph, Collection<Node> startingNodes) {
        for(Node startingPoint : startingNodes) {
            HashMap<Character, Integer> longestDistanceTo = new HashMap<Character, Integer>();
            HashMap<Node, Boolean> visited = new HashMap<>();

            for(Node node : graph.getNodes()) {
                visited.put(node, false);
            }

            visited.put(startingPoint, true);
            List<Node> nodesToVisit = new ArrayList<>();
            nodesToVisit.addAll(startingPoint.getAdjacentNodes());
            filterNodesToVisit(nodesToVisit, startingPoint);

            Node currentNode = startingPoint;

            int currentDistance = 0;
            while(nodesToVisit.size()>0) {
                Node nextNode = nodesToVisit.get(0);
                if(!visited.get(nextNode)) {
                    visited.put(nextNode, true);
                    List<Node> nodesToVisit2 = new ArrayList<>();
                    nodesToVisit.addAll(nextNode.getAdjacentNodes());
                    filterNodesToVisit(nodesToVisit2, startingPoint);
                }

            }
        }
    }

    public void filterNodesToVisit(List<Node> list, Node currentNode) {
        for(Node node : list) {
            if(node.getValue() - currentNode.getValue()!=1)
                list.remove(node);
        }
    }


}
