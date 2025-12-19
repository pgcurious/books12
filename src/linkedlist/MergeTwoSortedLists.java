package linkedlist;

/**
 * LeetCode #21 - Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Difficulty: Easy
 *
 * Problem: Merge two sorted linked lists and return a new sorted list.
 *
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 */
public class MergeTwoSortedLists {

    /**
     * INTUITION:
     * -----------
     * Similar to merging two sorted arrays. Compare heads of both lists,
     * take the smaller one, and advance that list's pointer.
     *
     * Use a dummy node to simplify edge cases (no special handling for head).
     *
     * Steps:
     * 1. Create dummy node as placeholder
     * 2. Compare list1 and list2 heads
     * 3. Attach smaller node to result
     * 4. Move that list's pointer forward
     * 5. Repeat until one list is exhausted
     * 6. Attach remaining list
     *
     * Time: O(n + m)
     * Space: O(1)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node simplifies head handling
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Compare and attach smaller node
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes (one list might still have nodes)
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    /**
     * Recursive approach (elegant but uses stack space)
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Take smaller node and recursively merge rest
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        // Test case 1
        ListNode list1 = ListNode.fromArray(new int[]{1, 2, 4});
        ListNode list2 = ListNode.fromArray(new int[]{1, 3, 4});
        System.out.println("List1: " + ListNode.toString(list1));
        System.out.println("List2: " + ListNode.toString(list2));
        ListNode merged = solution.mergeTwoLists(list1, list2);
        System.out.println("Merged: " + ListNode.toString(merged));
        // Expected: [1 -> 1 -> 2 -> 3 -> 4 -> 4]

        // Test case 2
        ListNode list3 = null;
        ListNode list4 = ListNode.fromArray(new int[]{0});
        System.out.println("\nList1: " + ListNode.toString(list3));
        System.out.println("List2: " + ListNode.toString(list4));
        ListNode merged2 = solution.mergeTwoLists(list3, list4);
        System.out.println("Merged: " + ListNode.toString(merged2));
        // Expected: [0]
    }
}
