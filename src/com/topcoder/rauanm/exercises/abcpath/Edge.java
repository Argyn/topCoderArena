package com.topcoder.rauanm.exercises.abcpath;

/**
 * Created by Argyn on 04/10/2015.
 */
public class Edge {

    private Node node1;
    private Node node2;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;

        this.node2 = node2;

        node1.addAdjacentNode(node2);
        node2.addAdjacentNode(node1);
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    @Override
    public String toString() {
        return node1.getValue()+" <---> "+node2.getValue();
    }
}
