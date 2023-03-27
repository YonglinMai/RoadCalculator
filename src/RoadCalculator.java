/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 7
 * Recitation: 01
 */

import big.data.DataSource;
import big.data.DataSourceException;


import java.util.*;

/**
 * A fully-documented driver class for the program.
 */
public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    private static ArrayList<Edge> edgeList = new ArrayList<>();
    private static ArrayList<Node> finalArray = new ArrayList<>();

    /**
     * This method uses Big Data to generate a connected graph of Nodes and Edges.
     * @param location          :
     *                          the URL of the file
     * @return                  :
     *                          A connected graph of Nodes and Edges.
     * @throws DataSourceException  :
     *                              Throws DataSourceException if URl info can not be fetched.
     */
    public static HashMap<String, Node> buildGraph(String location) throws DataSourceException{
        HashMap<String,Node> cities = new HashMap<>();
        DataSource ds = DataSource.connect(location).load();

        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");

        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");

        // Fill the HashMap here...
        for (String i: cityNames){
            Node newNode = new Node(i);
            cities.put(i,newNode);
        }

        for (String i: roadNames){
            String[] stringList = i.replace("\"","").split(",");

            Node src = cities.get(stringList[0]);

            Node des = cities.get(stringList[1]);
            int weight = Integer.parseInt(stringList[2]);

            Edge newEdge = new Edge(src,des,weight);
            Edge reverseEdge = new Edge(des, src, weight);

            src.getEdges().add(newEdge);
            des.getEdges().add(reverseEdge);
            edgeList.add(newEdge);
        }
        return cities;
    }

    /**
     *  This method construct a connected Minimum Spanning Tree in the form of a Linked List
     * @param graph         :
     *                      A connected graph of Nodes and Edges.
     * @return              :
     *                      A connected Minimum Spanning Tree in the form of a Linked List
     */
    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph){
        //set an arraylist of visited
        ArrayList<Node> visited = new ArrayList<>();
        //set an arraylist of visited edges
        LinkedList<Edge> visitedEdge = new LinkedList<>();
        //set temp arraylist with all possible edges
        ArrayList<Edge> temp = new ArrayList<>();
        //while loop visited < the total number of node
        //sort the edges by weight min to max
        //select the min
        //add edge destination to the visited node
        //add edge destination's edges to the templist
        //remove edge
        // add removed edge to visited edge
        Node node = (Node) graph.values().toArray()[1];
        temp.addAll(node.getEdges());
        Collections.sort(temp);
        Edge edge = temp.remove(0);
        visitedEdge.add(edge);
        visited.add(node);
        visited.add(edge.getB());


        while(visited.size() < graph.size()){
            if (!temp.isEmpty()){
                Collections.sort(temp);
                Edge tempEdge = temp.remove(0);
                if(!visited.contains(tempEdge.getB())){
                    visitedEdge.add(tempEdge);
                    visited.add(tempEdge.getB());
                    for (Edge e: tempEdge.getB().getEdges()){
                        if (!visited.contains(e.getB())){
                            temp.add(e);
                        }
                    }

                }
            }
        }
        System.out.println("Minimum Spanning Tree:");
        System.out.println();
        for (Edge e: visitedEdge){
            System.out.println(e);
        }
        return visitedEdge;
    }

    /**
     * This method find the cost of the cheapest path from source to dest.
     * @param graph             :
     *                          A connected graph of Nodes and Edges.
     * @param source            :
     *                          The name of the start Node.
     * @param dest              :
     *                          The name of the destination Node.
     * @return                  :
     *                          The cost of the cheapest path from source to dest.
     */
    public static int Dijkstra(HashMap<String,Node> graph, String source, String dest){
            Node src = graph.get(source);
            Node des = graph.get(dest);

            HashMap<Node, Node> prev = new HashMap<>();
            ArrayList<Node> queue = new ArrayList<>();
            ArrayList<Node> visited = new ArrayList<>();

            src.setDistance(0);
            queue.add(src);

            for (Node n: graph.values()){
                if(!n.equals(src))
                    n.setDistance(Integer.MAX_VALUE);
            }

            while (!queue.isEmpty()){
                queue.sort(new comparator());
                Node min = queue.remove(0);

                for (Edge e: min.getEdges()){
                    Node next = e.getB();
                    if (!visited.contains(next)){
                        int alt = min.getDistance() + e.getCost();
                        if (alt < next.getDistance()){
                            next.setDistance(alt);
                            prev.put(next, min);
                            visited.add(next);
                        }
                        queue.add(next);
                    }
                }

            }

            finalArray = dijkstraHelper(prev, src, des);
            return des.getDistance();
    }

    /**
     * This method helps to generate the path from the start Node to the destination Node.
     * @param prev          :
     *                      HashMap of Node to Node, value is the Node that is previous of the key Node.
     * @param src           :
     *                      The beginning Node.
     * @param des           :
     *                      The destination Node.
     * @return              :
     *                      A Arraylist of Nodes that connects the start node to the destination Node.
     */
    public static ArrayList<Node> dijkstraHelper(HashMap<Node, Node> prev, Node src, Node des){
       if (prev.get(des).equals(src)){
           ArrayList<Node> array = new ArrayList<>();
           return array;
       }
       else{
           Node newNode = prev.get(des);
           ArrayList<Node> array = new ArrayList<>();
           array.add(src);
           ArrayList<Node> prevlist = dijkstraHelper(prev,src,newNode);
           array.addAll(prevlist);
           array.add(newNode);
           return array;
       }

    }

    /**
     * The main driver method that executes the program.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter graph URL");
        String location = input.next();

        try{
            graph = buildGraph(location);
        }catch (DataSourceException e){
            System.out.println("Please enter graph URL");
            location = input.next();
            graph = buildGraph(location);
        }



        System.out.println("Loading Map...");
        System.out.println("Cities:");
        System.out.println();
        for (Node i: graph.values())
            System.out.println(i.getName());

        System.out.println();

        System.out.println("Roads: ");
        System.out.println();
        for (Edge e: edgeList)
            System.out.println(e.getA().getName() + " to " + e.getB().getName() + " " + e.getCost());

        System.out.println();
        buildMST(graph);
        //System.out.println(buildMST(graph));


        //System.out.println("Enter a starting point for shortest path or Q to quit:");
        String option = "apple";

        do{
            System.out.println();
            System.out.println("Enter a starting point for shortest path or Q to quit:");
            option = input.nextLine();
            switch (option){
                case "q":
                case "Q":
                    System.out.println("Goodbye.");
                    break;
                default:
                    if (graph.containsKey(option)) {
                        String src = option;
                        System.out.println("Enter a destination:");
                        String des = input.nextLine();
                        if (graph.containsKey(des)) {
                            try{
                                if (src.equalsIgnoreCase(des))
                                    System.out.println(src + " to " + des + "\nDistance: " + 0);
                                else{
                                    System.out.println(option + " to " + des + "\nDistance: " + Dijkstra(graph, src, des));
                                    String str = "Via: ";
                                    for (Node n : finalArray) {
                                        str = str + n.getName() + ", ";
                                    }
                                    System.out.println(str);
                                }
                            }catch(NullPointerException e){
                                System.out.println("Invalid path");
                            }
                        }
                    }
            }
        }while(!option.equalsIgnoreCase("q"));

    }
}
