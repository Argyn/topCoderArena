package com.topcoder.rauanm.exercises.abcpath;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Argyn on 04/10/2015.
 */
public class Node implements Comparable<Node> {

    private char value;
    private Set<Node> adjacentNodes;

    public Node(char value) {
        this.value = value;
        adjacentNodes = new HashSet<>();
    }

    public char getValue() {
        return value;
    }

    public void addAdjacentNode(Node node) {
        adjacentNodes.add(node);
    }

    public Set<Node> getAdjacentNodes() {
        return adjacentNodes;
    }


    @Override
    public int compareTo(Node o) {
        if(o.getValue() > getValue())
            return -1;
        else if(o.getValue()==getValue())
            return 0;
        return 1;
    }
}
