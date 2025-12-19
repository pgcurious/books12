package linkedlist;

/**
 * LeetCode #142 - Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Difficulty: Medium
 *
 * Problem: Given head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * Example:
 * Input: head = [3,2,0,-4] where -4 connects back to 2
 * Output: node with value 2 (the start of cycle)
 */
public class LinkedListCycleII {

    /**
     * INTUITION:
     * -----------
     * This builds on Floyd's algorithm but with a mathematical twist.
     *
     * Let's define:
     * - F = distance from head to cycle start
     * - a = distance from cycle start to meeting point
     * - C = cycle length
     *
     * When slow and fast meet:
     * - Slow traveled: F + a
     * - Fast traveled: F + a + n*C (where n >= 1, some complete cycles)
     *
     * Since fast travels 2x slow's speed:
     * 2(F + a) = F + a + n*C
     * F + a = n*C
     * F = n*C - a = (n-1)*C + (C-a)
     *
     * This means: Distance from head to cycle start (F) equals
     * distance from meeting point to cycle start going forward!
     *
     * Algorithm:
     * 1. Find meeting point using slow/fast
     * 2. Reset one pointer to head
     * 3. Move both one step at a time
     * 4. They'll meet at cycle start!
     *
     * Time: O(n)
     * Space: O(1)
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // Phase 1: Find meeting point
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Phase 2: Find cycle start
                ListNode pointer1 = head;
                ListNode pointer2 = slow;

                while (pointer1 != pointer2) {
                    pointer1 = pointer1.next;
                    pointer2 = pointer2.next;
                }

                return pointer1;  // Cycle start
            }
        }

        return null;  // No cycle
    }

    public static void main(String[] args) {
        LinkedListCycleII solution = new LinkedListCycleII();

        // Test case 1: Cycle at position 1
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // Creates cycle back to node2

        ListNode cycleStart = solution.detectCycle(head1);
        System.out.println("List: [3,2,0,-4] with cycle");
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.val : "null"));
        // Expected: 2

        // Test case 2: No cycle
        ListNode head2 = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode cycleStart2 = solution.detectCycle(head2);
        System.out.println("\nList: [1,2,3] (no cycle)");
        System.out.println("Cycle starts at: " + (cycleStart2 != null ? cycleStart2.val : "null"));
        // Expected: null
    }
}
