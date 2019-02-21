package cn.jason.lee.advance.q2;

public class Answer1 {

    public static void main(String[] args) {
        Node[] nodes = {new Node("A", 1),
                new Node("B", 2),
                new Node("C", 2)};
        Link[] links = {new Link("A", "B"),
                new Link("B", "C"),
                new Link("A", "C")};

        Graph graph = new Graph(nodes, links);
        graph.calculateMaxWeight("A", "C");
    }
};
