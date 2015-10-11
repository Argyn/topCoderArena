package com.topcoder.rauanm.exercises.jumpingonpoints;

import java.util.*;

/**
 * Created by rauanm on 06/10/2015.
 */
public class JumpingOnPoints {

    private long[] X;
    private long[] Y;
    private long[] P;
    private long[] T;

    public JumpingOnPoints() {

    }

    public long sumOfDistances(int N, int S, int[] params) {
        generateValues(N, params);
        Graph graph = buildGraph();
        //printGeneratedValues();#
        printGraph(graph);
        testCorrectAnswer();
        findDistanceFromToAll(graph, S);
        long sumDistance = 0L;

        for(Node node : graph.getNodes()) {
            long distanceToPoint = node.getDistance();
            if(distanceToPoint==Long.MAX_VALUE)
                sumDistance += 1.5E14;
            else
                sumDistance += distanceToPoint;
        }

        return sumDistance;
    }

    public void generateValues(int N, int[] params) {
        X = new long[N];
        Y = new long[N];
        P = new long[N];
        T = new long[N];

        X[0] = params[0];
        Y[0] = params[4];
        P[0] = params[8];
        T[0] = params[12];

        for (int i = 1; i < N; i++) {
            X[i] = (X[i - 1] * params[1] + params[2]) % params[3];
            Y[i] = (Y[i - 1] * params[5] + params[6]) % params[7];
            P[i] = (P[i - 1] * params[9] + params[10]) % params[11];
            T[i] = (T[i - 1] * params[13] + params[14]) % params[15];
        }
    }

    public void testCorrectAnswer() {
        long resultSum = 0L;
        for(int i=0; i<T.length; i++) {
            resultSum += T[i];
        }

        System.out.println("Result sum:"+resultSum);
    }

    public void printGeneratedValues() {
        for(int i=0; i<X.length; i++) {
            System.out.println("X,Y : ("+X[i]+","+Y[i]+"), T="+T[i]+", P="+P[i]);
        }
    }

    public Graph buildGraph() {
        Graph graph = new Graph();
        List<Node> nodes = new ArrayList<>();
        for(int i=0; i<X.length; i++) {
            Node node = new Node(X[i], Y[i], P[i], T[i]);;
            nodes.add(i, node);
            graph.addNode(node);
        }

        for(int i=0; i<X.length; i++) {
            for(int j=0; j<X.length; j++) {
                if(i!=j) {
                    if(checkThereIsAnArrow(i, j))
                        graph.addEdge(nodes.get(i), nodes.get(j));
                }
            }
        }

        return graph;
    }

    public boolean checkThereIsAnArrow(int i, int j) {
        return Math.abs(X[i]-X[j])<=P[i] || Math.abs(Y[i]-Y[j])<=P[i];
    }

    public void printGraph(Graph graph) {
        for(Node node : graph.getNodes()) {
            if(node.getAdjacentNodes().size()>0) {
                for(Node adjNode : node.getAdjacentNodes()) {
                    System.out.println(node+" ----> "+adjNode);
                }
            }
        }
    }


    public void findDistanceFromToAll(Graph graph, int sourcePoint) {
        List<Node> allNodes = new ArrayList<>(graph.getNodes());
        allNodes.get(sourcePoint).setDistance(0);

        //System.out.println("Source node:"+allNodes.get(sourcePoint)+", Distance:"+allNodes.get(sourcePoint).getDistance());

        Queue<Node> queue = new PriorityQueue<>(allNodes);
        while(queue.size()>0) {
            Node currentNode = queue.poll();
            //System.out.println("Current node:"+currentNode+", Distance:"+currentNode.getDistance());
            for(Node adjNode : currentNode.getAdjacentNodes()) {
                //System.out.println("Considering adj node"+adjNode+" of "+currentNode);
                if(currentNode.getDistance()!=Long.MAX_VALUE) {
                    long newDistance = currentNode.getDistance() + max(adjNode.getT(), currentNode.getT());
                    if(newDistance < adjNode.getDistance()) {
                        //System.out.println("Assigning distance " + newDistance + " to " + adjNode);
                        adjNode.setDistance(newDistance);
                    }
                }
            }
        }
    }

    public long max(long num1, long num2) {
        // System.out.println("Considering max values "+num1+" and "+num2);
        if(num1>num2)
            return num1;
        return num2;
    }

    public class Edge {

        private Node node1;
        private Node node2;

        public Edge(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
            node1.addAdjacentNodes(node2);
        }

        public Node getNode1() {
            return node1;
        }

        public Node getNode2() {
            return node2;
        }
    }

    public class Graph {

        private List<Edge> edges;
        private List<Node> nodes;

        public Graph() {
            edges = new ArrayList<>();
            nodes = new ArrayList<>();
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public Collection<Node> getNodes() {
            return nodes;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        public void addEdge(Node node1, Node node2) {
            edges.add(new Edge(node1, node2));
        }

        public Collection<Edge> getEdges() {
            return edges;
        }
    }

    public class Node implements Comparable<Node> {

        private long x;
        private long y;
        private long P;
        private long T;
        private long distance;

        public Collection<Node> adjacentNodes;

        public Node(long x, long y, long P, long T) {
            this.x = x;
            this.y = y;
            this.P = P;
            this.T = T;
            this.distance = Long.MAX_VALUE;

            adjacentNodes = new HashSet<>();
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        public long getP() {
            return P;
        }

        public long getT() {
            return T;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public long getDistance() {
            return distance;
        }

        public void addAdjacentNodes(Node node) {
            adjacentNodes.add(node);
        }

        public Collection<Node> getAdjacentNodes() {
            return adjacentNodes;
        }

        @Override
        public String toString() {
            return "("+x+" , "+y+"), P="+P+", T="+T;
        }

        @Override
        public int compareTo(Node o) {
            if(distance<o.distance)
                return -1;
            if(distance == o.distance)
                return 0;
            return 1;
        }
    }
}