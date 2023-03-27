/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 7
 * Recitation: 01
 */

import java.util.Comparator;

/**
 * A comparator class that compares two Nodes
 */
public class comparator implements Comparator<Node>{
    /**
     * The default compare method between Node 1 and Node 2
     * @param o1        :
     *                  Node 1
     * @param o2        :
     *                  Node 2
     * @return          :
     *                  -1 if the Node 1 distance is less than other Node's distance ,
     *                  0 if equal,
     *                  1 if greater than.
     */
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getDistance(), o2.getDistance());
    }
}
