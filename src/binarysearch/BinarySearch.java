package binarysearch;

/**
 * LeetCode #704 - Binary Search
 * https://leetcode.com/problems/binary-search/
 *
 * Difficulty: Easy
 *
 * Problem: Given a sorted array of integers and a target value,
 * return the index if found, otherwise return -1.
 *
 * Example:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 */
public class BinarySearch {

    /**
     * INTUITION:
     * -----------
     * Binary search is THE fundamental divide-and-conquer algorithm.
     *
     * For a SORTED array:
     * 1. Look at middle element
     * 2. If target = middle, found it!
     * 3. If target < middle, search left half
     * 4. If target > middle, search right half
     * 5. Repeat until found or search space exhausted
     *
     * Each step eliminates HALF the remaining elements!
     *
     * Key details:
     * - Use left + (right - left) / 2 to avoid integer overflow
     * - Left and right are INCLUSIVE bounds [left, right]
     *
     * Time: O(log n)
     * Space: O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Avoid overflow: don't use (left + right) / 2
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;  // Found!
            } else if (nums[mid] < target) {
                left = mid + 1;   // Search right half
            } else {
                right = mid - 1;  // Search left half
            }
        }

        return -1;  // Not found
    }

    /**
     * Recursive version (same logic, uses stack space)
     */
    public int searchRecursive(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }

    private int binarySearchHelper(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearchHelper(nums, target, mid + 1, right);
        } else {
            return binarySearchHelper(nums, target, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();

        // Test case 1
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        System.out.println("Array: [-1,0,3,5,9,12], Target: 9");
        System.out.println("Index: " + solution.search(nums1, 9));
        // Expected: 4

        // Test case 2
        System.out.println("\nArray: [-1,0,3,5,9,12], Target: 2");
        System.out.println("Index: " + solution.search(nums1, 2));
        // Expected: -1

        // Test case 3
        int[] nums2 = {5};
        System.out.println("\nArray: [5], Target: 5");
        System.out.println("Index: " + solution.search(nums2, 5));
        // Expected: 0
    }
}
