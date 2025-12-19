package trees;

import java.util.*;

/**
 * LeetCode #226 - Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Difficulty: Easy
 *
 * Problem: Invert a binary tree (mirror it).
 *
 * Example:
 * Input:      4            Output:     4
 *           /   \                    /   \
 *          2     7                  7     2
 *         / \   / \                / \   / \
 *        1   3 6   9              9   6 3   1
 */
public class InvertBinaryTree {

    /**
     * INTUITION:
     * -----------
     * Inverting = swapping left and right children at EVERY node.
     *
     * Recursive approach:
     * 1. Swap current node's children
     * 2. Recursively invert left subtree
     * 3. Recursively invert right subtree
     *
     * Order doesn't matter for steps 1-3 (preorder, postorder both work)
     *
     * Time: O(n)
     * Space: O(h) for recursion stack
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Swap children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * Alternative: Iterative using BFS
     */
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // Add children to queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();

        // Test case 1
        TreeNode root = TreeNode.fromArray(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        System.out.println("Original tree:");
        TreeNode.printTree(root);

        TreeNode inverted = solution.invertTree(root);
        System.out.println("\nInverted tree:");
        TreeNode.printTree(inverted);
        // Children should be swapped at every level
    }
}
