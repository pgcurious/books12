package linkedlist;

/**
 * LeetCode #141 - Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Difficulty: Easy
 *
 * Problem: Given head of a linked list, determine if it has a cycle.
 *
 * Example:
 * Input: head = [3,2,0,-4] where -4 connects back to 2
 * Output: true
 */
public class LinkedListCycle {

    /**
     * INTUITION (Floyd's Cycle Detection / Tortoise and Hare):
     * ---------------------------------------------------------
     * Imagine two runners on a circular track:
     * - Slow runner moves 1 step at a time
     * - Fast runner moves 2 steps at a time
     *
     * If there's a cycle, fast will eventually lap slow (they'll meet).
     * If no cycle, fast reaches the end (null).
     *
     * Why does this work?
     * - Fast gains 1 step on slow each iteration
     * - If there's a cycle of length k, fast will catch up in k iterations
     *
     * Time: O(n)
     * Space: O(1)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move 1 step
            fast = fast.next.next;  // Move 2 steps

            if (slow == fast) {
                return true;  // They met - cycle exists!
            }
        }

        return false;  // Fast reached end - no cycle
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test case 1: Cycle exists
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Creates cycle back to node2

        System.out.println("List with cycle at position 1");
        System.out.println("Has cycle: " + solution.hasCycle(head1));
        // Expected: true

        // Test case 2: No cycle
        ListNode head2 = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("\nList: [1,2,3,4,5] (no cycle)");
        System.out.println("Has cycle: " + solution.hasCycle(head2));
        // Expected: false

        // Test case 3: Single node, no cycle
        ListNode head3 = new ListNode(1);
        System.out.println("\nSingle node [1]");
        System.out.println("Has cycle: " + solution.hasCycle(head3));
        // Expected: false
    }
}
