package trees;

import java.util.*;

/**
 * Definition for a binary tree node.
 * This class is used by all tree problems.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Create tree from level-order array (null for missing nodes)
     * Example: [3,9,20,null,null,15,7] creates:
     *       3
     *      / \
     *     9  20
     *       /  \
     *      15   7
     */
    public static TreeNode fromArray(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode node = queue.poll();

            // Left child
            if (i < arr.length && arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.offer(node.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * Print tree in a visual format
     */
    public static void printTree(TreeNode root) {
        printTree(root, "", false);
    }

    private static void printTree(TreeNode node, String prefix, boolean isRight) {
        if (node != null) {
            System.out.println(prefix + (isRight ? "├── " : "└── ") + node.val);
            printTree(node.left, prefix + (isRight ? "│   " : "    "), true);
            printTree(node.right, prefix + (isRight ? "│   " : "    "), false);
        }
    }
}
