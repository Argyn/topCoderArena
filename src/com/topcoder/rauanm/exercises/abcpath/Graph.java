package com.topcoder.rauanm.exercises.abcpath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Argyn on 04/10/2015.
 */
public class Graph {

    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdge(Node node1, Node node2) {
        edges.add(new Edge(node1, node2));
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
