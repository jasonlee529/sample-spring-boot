package cn.jason.lee.advance.q2;

import java.util.*;

/**
 * 一个无向图
 */
public class Graph {
    private Node[] nodes;
    private Link[] links;
    Map<Node, List<Link>> nodeIndex = new HashMap<Node, List<Link>>();


    public Graph(Node[] nodes, Link[] links) {
        this.nodes = nodes;
        this.links = links;
        for (Node node : nodes) {
            List<Link> l = new ArrayList<Link>();
            for (Link link : links) {
                if (node.name.equals(link.srcName)) {
                    link.src = node;
                    l.add(link);
                }
                if (node.name.equals(link.destName)) {
                    link.dest = node;
                }
            }
            nodeIndex.put(node, l);
        }
    }


    public void calculateMaxWeight(String start, String end) {
        Node startNode = findNode(start);
        Node endNode = findNode(end);
        generatePaths(startNode, endNode, new LinkedList<Node>());
        Collections.sort(paths, new Comparator<Queue<Node>>() {
            @Override
            public int compare(Queue<Node> o1, Queue<Node> o2) {
                int w1 = getWeight(o1);
                int w2 = getWeight((Queue<Node>) o2);
                return w2 - w1;
            }
        });
        Queue<Node> max = paths.get(0);
        System.out.println(toStringNodes(max) + "===========" + getWeight(max));
    }

    private String toStringNodes(Queue<Node> max) {
        String seq = "";
        StringBuilder sb = new StringBuilder();
        for (Node n : max) {
            sb.append(seq).append(n.name);
            seq = ",";
        }
        return sb.toString();
    }

    private int getWeight(Queue<Node> o2) {
        int w2 = 0;
        for (Node n : o2) {
            w2 += n.weight;
        }
        return w2;
    }

    private Node findNode(String name) {
        for (Node n : nodes) {
            if (name.equals(n.name)) {
                return n;
            }
        }
        return null;
    }

    private int verticeNum;
    private int[] visitedCount;
    private int[] currDist;

    List<Queue<Node>> paths = new ArrayList<Queue<Node>>();

    public void generatePaths(Node start, Node end, Queue<Node> visited) {
        visited.offer(start);
        if (start.equals(end)) {
            return;
        }
        if (nodeIndex.get(start) != null) {
            List<Link> links = nodeIndex.get(start);
            for (Link link : links) {
                if (link.destName.equals(end.name)) {
                    visited.offer(end);
                    paths.add(copyVistted(visited));
                    visited.poll();
                    break;
                } else {
                    generatePaths(link.dest, end, visited);
                    visited.poll();
                }
            }
        }
    }

    private Queue<Node> copyVistted(Queue<Node> visited) {
        Queue<Node> newNodes = new LinkedList<Node>();
        for (Node node : visited) {
            newNodes.offer(node);
        }
        return newNodes;
    }

}

class Node {
    String name;
    int weight;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}

class Link {
    Node src;
    String srcName;
    Node dest;
    String destName;

    public Link(String srcName, String destName) {
        this.srcName = srcName;
        this.destName = destName;
    }
}