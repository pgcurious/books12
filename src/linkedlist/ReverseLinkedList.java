package linkedlist;

/**
 * LeetCode #206 - Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Difficulty: Easy
 *
 * Problem: Given the head of a singly linked list, reverse the list
 * and return the reversed list.
 *
 * Example:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 */
public class ReverseLinkedList {

    /**
     * INTUITION (Iterative):
     * -----------------------
     * We need to flip all the arrows to point backward.
     *
     * Use three pointers:
     * - prev: the node we're pointing TO (starts as null)
     * - curr: the node we're currently processing
     * - next: save the next node before we break the link
     *
     * At each step:
     * 1. Save next node (we'll lose it when we flip the arrow)
     * 2. Flip arrow: curr.next = prev
     * 3. Move pointers forward: prev = curr, curr = next
     *
     * Visualization:
     * null <- 1    2 -> 3 -> 4 -> 5
     *       prev  curr next
     *
     * Time: O(n)
     * Space: O(1)
     */
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;  // Save next node
            curr.next = prev;           // Flip the arrow
            prev = curr;                // Move prev forward
            curr = next;                // Move curr forward
        }

        return prev; // prev is the new head
    }

    /**
     * INTUITION (Recursive):
     * -----------------------
     * Trust the recursion to reverse everything after current node.
     * Then just fix the pointer for current node.
     *
     * Base case: empty or single node -> return as is
     * Recursive: reverse rest, then make next node point back to me
     *
     * Time: O(n)
     * Space: O(n) - recursion stack
     */
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest
        ListNode newHead = reverseListRecursive(head.next);

        // Make my next node point back to me
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // Test case 1
        ListNode head1 = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Input: " + ListNode.toString(head1));
        ListNode reversed1 = solution.reverseListIterative(head1);
        System.out.println("Output: " + ListNode.toString(reversed1));
        // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        // Test case 2
        ListNode head2 = ListNode.fromArray(new int[]{1, 2});
        System.out.println("\nInput: " + ListNode.toString(head2));
        ListNode reversed2 = solution.reverseListRecursive(head2);
        System.out.println("Output: " + ListNode.toString(reversed2));
        // Expected: [2 -> 1]

        // Test case 3
        ListNode head3 = null;
        System.out.println("\nInput: " + ListNode.toString(head3));
        ListNode reversed3 = solution.reverseListIterative(head3);
        System.out.println("Output: " + ListNode.toString(reversed3));
        // Expected: []
    }
}
