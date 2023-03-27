/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 7
 * Recitation: 01
 */

/**
 * A fully-documented driver class called RoadCalculator.
 */
public class Edge implements Comparable<Edge>{
    Node A;
    Node B;
    int cost;

    /**
     * A default constructor that initialize Node A, B and the weight of the edge.
     * @param a     :
     *              Node A of the Edge
     * @param b     :
     *              Node B of the Edge
     * @param weight        :
     *                      The weight of the Edge
     */
    public Edge(Node a, Node b, int weight) {
        A = a;
        B = b;
        cost = weight;
    }

    /**
     * The getter method that gets Node A of the Edge.
     * @return          :
     *                  Node A of the Edge
     */
    public Node getA() {
        return A;
    }

    /**
     * The getter method that gets Node B of the Edge.
     * @return          :
     *                  Node B of the Edge
     */
    public Node getB() {
        return B;
    }

    /**
     * The getter method that gets the cost of the Edge.
     * @return          :
     *                  cost of the Edge
     */
    public int getCost() {
        return cost;
    }

    /**
     * A comparator method that compares the cost of each edge.
     * @param o         :
     *                  The Edge being compare to.
     * @return          :
     *                  0  if costs are equal.
     *                  1  if cost is higher than compared.
     *                  -1 if cost is lower than compared.
     */
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(o.cost, cost);
    }

    /**
     * A ToString() method to print the roads in the graph
     * @return
     */
    public String toString(){
        return A.getName() + " to " + B.getName() + " " + cost;
    }
}
