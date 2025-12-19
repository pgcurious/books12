package trees;

import java.util.*;

/**
 * LeetCode #94, #144, #145 - Binary Tree Traversals
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Difficulty: Easy
 *
 * Problem: Traverse a binary tree in different orders.
 */
public class BinaryTreeTraversals {

    /**
     * INTUITION:
     * -----------
     * Three ways to visit nodes - differ in WHEN you process the current node:
     *
     * PREORDER: Node -> Left -> Right (process node FIRST)
     *   - Good for: copying tree, prefix expression
     *
     * INORDER: Left -> Node -> Right (process node in MIDDLE)
     *   - Good for: BST gives sorted order
     *
     * POSTORDER: Left -> Right -> Node (process node LAST)
     *   - Good for: deleting tree, postfix expression
     *
     * Memory trick: PRE=before, IN=middle, POST=after
     * (when to visit the node relative to children)
     */

    // ========== INORDER (Left -> Node -> Right) ==========

    /**
     * Inorder - Recursive (simple)
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);    // Left
        result.add(node.val);                 // Node
        inorderHelper(node.right, result);   // Right
    }

    /**
     * Inorder - Iterative (uses stack)
     * Key: Go left as far as possible, then process, then go right
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go left as far as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process current node
            current = stack.pop();
            result.add(current.val);

            // Move to right subtree
            current = current.right;
        }

        return result;
    }

    // ========== PREORDER (Node -> Left -> Right) ==========

    /**
     * Preorder - Recursive
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);                  // Node
        preorderHelper(node.left, result);     // Left
        preorderHelper(node.right, result);    // Right
    }

    /**
     * Preorder - Iterative
     * Key: Process node first, push right then left (so left is processed first)
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);  // Process node

            // Push right first so left is processed first
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return result;
    }

    // ========== POSTORDER (Left -> Right -> Node) ==========

    /**
     * Postorder - Recursive
     */
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorderHelper(node.left, result);    // Left
        postorderHelper(node.right, result);   // Right
        result.add(node.val);                   // Node
    }

    /**
     * Postorder - Iterative
     * Trick: Postorder is reverse of "Node -> Right -> Left"
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.addFirst(node.val);  // Add to front (reverse)

            // Push left first so right is processed first
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        return result;
    }

    // ========== LEVEL ORDER (BFS) ==========

    /**
     * LeetCode #102 - Binary Tree Level Order Traversal
     * https://leetcode.com/problems/binary-tree-level-order-traversal/
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryTreeTraversals solution = new BinaryTreeTraversals();

        // Create tree:    1
        //                / \
        //               2   3
        //              / \
        //             4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Tree structure:");
        TreeNode.printTree(root);

        System.out.println("\nInorder (Left-Node-Right): " + solution.inorderTraversalRecursive(root));
        // Expected: [4, 2, 5, 1, 3]

        System.out.println("Preorder (Node-Left-Right): " + solution.preorderTraversalRecursive(root));
        // Expected: [1, 2, 4, 5, 3]

        System.out.println("Postorder (Left-Right-Node): " + solution.postorderTraversalRecursive(root));
        // Expected: [4, 5, 2, 3, 1]

        System.out.println("Level Order (BFS): " + solution.levelOrder(root));
        // Expected: [[1], [2, 3], [4, 5]]
    }
}
