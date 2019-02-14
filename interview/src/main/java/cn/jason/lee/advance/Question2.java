package cn.jason.lee.advance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路径规划
 */
public class Question2 {


    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>() {
            {
                add(new Node("A", 1));
                add(new Node("B", 2));
                add(new Node("C", 2));
            }
        };
        List<Link> links = new ArrayList<Link>() {
            {
                add(new Link("A", "B"));
                add(new Link("B", "C"));
                add(new Link("A", "C"));
            }
        };
        Map<Node, List<Link>> nodeIndex = new HashMap<Node, List<Link>>();
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
        String begin = "A";
        String end = "C";
        int w = 0;
        List<Link> path = new ArrayList<Link>();
        Map<List<Link>, Integer> paths = new HashMap<List<Link>, Integer>();
        Node beginNode = null;
        Node endNode = null;
        for (Node node : nodes) {
            if (begin.equals(node.name)) {
                beginNode = node;
            }
            if (end.equals(node.name)) {
                endNode = node;
            }
        }
        generate(nodeIndex,beginNode,endNode,path);

    }

    /**
     * 改为深度有限搜索算法实现
     * @param nodeIndex
     * @param curent
     * @param dest
     * @param parent
     */
    static void generate(Map<Node, List<Link>> nodeIndex, Node curent,Node dest, List<Link> parent) {
        System.out.println(curent.name);
        List<Link> currentLinks = nodeIndex.get(curent);
        for(Link link : currentLinks){
            parent.add(link);
            if(link.dest.equals(dest.name)){
                System.out.println(parent.toString());
                break;
            }else{
                generate(nodeIndex,link.dest,dest,parent);
            }
        }
    }

    static class Node {
        String name;
        int weight;

        public Node(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }
    }

    static class Link {
        Node src;
        String srcName;
        Node dest;
        String destName;

        public Link(String srcName, String destName) {
            this.srcName = srcName;
            this.destName = destName;
        }
    }
}
