package trees;

import java.util.*;

/**
 * LeetCode #104 - Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Difficulty: Easy
 *
 * Problem: Given the root of a binary tree, return its maximum depth.
 * Maximum depth = number of nodes along the longest path from root to leaf.
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class MaximumDepthOfBinaryTree {

    /**
     * INTUITION (Recursive DFS):
     * ---------------------------
     * Depth of a tree = 1 + max(depth of left subtree, depth of right subtree)
     *
     * Base case: null node has depth 0
     * Recursive: current depth = 1 + max of children depths
     *
     * Time: O(n) - visit every node
     * Space: O(h) - recursion stack, h = height
     */
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepthRecursive(root.left);
        int rightDepth = maxDepthRecursive(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * One-liner version (same logic)
     */
    public int maxDepthOneLiner(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepthOneLiner(root.left), maxDepthOneLiner(root.right));
    }

    /**
     * INTUITION (Iterative BFS):
     * ---------------------------
     * Level order traversal - count number of levels.
     * Each level we complete adds 1 to depth.
     *
     * Time: O(n)
     * Space: O(n) - queue size
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();

        // Test case 1
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        TreeNode root1 = TreeNode.fromArray(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Tree 1:");
        TreeNode.printTree(root1);
        System.out.println("Max depth: " + solution.maxDepthRecursive(root1));
        // Expected: 3

        // Test case 2
        TreeNode root2 = TreeNode.fromArray(new Integer[]{1, null, 2});
        System.out.println("\nTree 2:");
        TreeNode.printTree(root2);
        System.out.println("Max depth: " + solution.maxDepthRecursive(root2));
        // Expected: 2
    }
}
