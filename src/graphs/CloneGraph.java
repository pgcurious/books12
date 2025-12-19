package graphs;

import java.util.*;

/**
 * LeetCode #133 - Clone Graph
 * https://leetcode.com/problems/clone-graph/
 *
 * Difficulty: Medium
 *
 * Problem: Given a reference of a node in a connected undirected graph,
 * return a deep copy (clone) of the graph.
 */
public class CloneGraph {

    // Graph node definition
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * INTUITION:
     * -----------
     * We need to create new nodes and maintain the same connections.
     *
     * Challenge: Avoid infinite loops in cyclic graphs!
     *
     * Strategy:
     * 1. Use a HashMap to track: original node -> cloned node
     * 2. For each node, create its clone if not already created
     * 3. Recursively clone all neighbors
     * 4. Connect cloned node to cloned neighbors
     *
     * The HashMap serves two purposes:
     * - Avoid creating duplicate clones
     * - Detect cycles (if already in map, we've seen it)
     *
     * Time: O(N + E) - visit all nodes and edges
     * Space: O(N) - HashMap and recursion stack
     */
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create clone (without neighbors initially)
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // Clone all neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }

    /**
     * Alternative: BFS approach
     */
    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;

        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // Create first clone and add to queue
        visited.put(node, new Node(node.val));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Process all neighbors
            for (Node neighbor : current.neighbors) {
                // Clone neighbor if not already cloned
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }

                // Connect current clone to neighbor clone
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }

    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();

        // Create graph: 1 -- 2
        //               |    |
        //               4 -- 3
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        System.out.println("Original graph node 1 neighbors: " +
            node1.neighbors.stream().map(n -> n.val).toList());

        Node clone = solution.cloneGraph(node1);

        System.out.println("Cloned graph node 1 neighbors: " +
            clone.neighbors.stream().map(n -> n.val).toList());

        System.out.println("\nOriginal node1 == Clone: " + (node1 == clone));
        // Expected: false (different objects)

        System.out.println("Original node1.val == Clone.val: " + (node1.val == clone.val));
        // Expected: true (same values)
    }
}
