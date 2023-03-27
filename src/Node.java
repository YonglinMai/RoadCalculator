/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 7
 * Recitation: 01
 */

import java.util.HashSet;
import java.util.LinkedList;

/**
 * A fully-documented class named Node that represents a City that will be a part of our graph of connected cities.
 */
public class Node {
    String name;
    HashSet<Edge> edges;
    boolean visited;
    LinkedList<String> path;
    int distance;

    /**
     * The default constructor of the class initializing the name of the Node
     * @param name          :
     *                      The name of the Node.
     */
    public Node(String name) {
        this.name = name;
        visited = false;
        edges = new HashSet<>();
        path = new LinkedList<>();
        distance = 0;
    }

    /**
     * A getter method that gets collection of all of the edges that starts with the current Node
     * @return              :
     *                      collection of all of the edges that starts with the current Node
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * A getter method that gets the Name of the current Node.
     * @return          :
     *                  The name of the current Node.
     */
    public String getName() {
        return name;
    }

    /**
     * The getter method that gets the length of the current known shortest path from the given node to the starting node in Dijkstra's algorithm
     * @return          :
     *                  length of the current known shortest path from the given node to the starting node in Dijkstra's algorithm
     */
    public int getDistance() {
        return distance;
    }

    /**
     * The setter method that sets the length of the current known shortest path from the given node to the starting node in Dijkstra's algorithm
     * @param distance              :
     *                              length of the current known shortest path from the given node to the starting node in Dijkstra's algorithm
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

}
