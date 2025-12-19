package trees;

import java.util.*;

/**
 * LeetCode #98 - Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Difficulty: Medium
 *
 * Problem: Given the root of a binary tree, determine if it is a valid BST.
 *
 * BST property:
 * - Left subtree contains only nodes LESS than current node
 * - Right subtree contains only nodes GREATER than current node
 * - Both subtrees must also be BSTs
 *
 * Example:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false (3 is in right subtree but less than 5)
 */
public class ValidateBinarySearchTree {

    /**
     * INTUITION:
     * -----------
     * Common mistake: Only checking node.left.val < node.val < node.right.val
     * This is WRONG because BST property applies to ENTIRE subtrees!
     *
     * Example of this mistake:
     *       5
     *      / \
     *     1   6
     *        / \
     *       3   7   <- 3 < 5, but it's in RIGHT subtree!
     *
     * Correct approach: Track valid range [min, max] for each node.
     * - Root can be any value
     * - Left child must be in (min, parent)
     * - Right child must be in (parent, max)
     *
     * Time: O(n)
     * Space: O(h) for recursion stack
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        // Check if current node violates the range
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        // Left subtree: values must be < current node
        // Right subtree: values must be > current node
        return isValidBSTHelper(node.left, min, node.val) &&
               isValidBSTHelper(node.right, node.val, max);
    }

    /**
     * Alternative: Inorder traversal should give sorted order
     * If any node is <= previous node, it's not a valid BST
     */
    public boolean isValidBSTInorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode prev = null;

        while (current != null || !stack.isEmpty()) {
            // Go left
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            // Check BST property: prev should be < current
            if (prev != null && prev.val >= current.val) {
                return false;
            }
            prev = current;

            current = current.right;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

        // Test case 1: Valid BST
        //     2
        //    / \
        //   1   3
        TreeNode root1 = TreeNode.fromArray(new Integer[]{2, 1, 3});
        System.out.println("Tree 1 (Valid BST):");
        TreeNode.printTree(root1);
        System.out.println("Is valid BST: " + solution.isValidBST(root1));
        // Expected: true

        // Test case 2: Invalid BST
        //     5
        //    / \
        //   1   4
        //      / \
        //     3   6
        TreeNode root2 = TreeNode.fromArray(new Integer[]{5, 1, 4, null, null, 3, 6});
        System.out.println("\nTree 2 (Invalid - 3 in right subtree but < 5):");
        TreeNode.printTree(root2);
        System.out.println("Is valid BST: " + solution.isValidBST(root2));
        // Expected: false

        // Test case 3: Tricky case
        //     5
        //    / \
        //   4   6
        //      / \
        //     3   7   <- 3 < 5 but in right subtree
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(4);
        root3.right = new TreeNode(6);
        root3.right.left = new TreeNode(3);
        root3.right.right = new TreeNode(7);
        System.out.println("\nTree 3 (Invalid - 3 in right subtree):");
        TreeNode.printTree(root3);
        System.out.println("Is valid BST: " + solution.isValidBST(root3));
        // Expected: false
    }
}
