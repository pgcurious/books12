package binarysearch;

/**
 * LeetCode #33 - Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Difficulty: Medium
 *
 * Problem: An originally sorted array was rotated at some pivot.
 * Given the rotated array and a target, return index if found, else -1.
 * (Array has no duplicates)
 *
 * Example:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * (Original: [0,1,2,4,5,6,7] rotated at index 4)
 */
public class SearchRotatedSortedArray {

    /**
     * INTUITION:
     * -----------
     * The array has TWO sorted halves. At least one half is always sorted.
     *
     * Strategy:
     * 1. Find middle element
     * 2. Determine which half is sorted
     * 3. Check if target is in the sorted half
     * 4. If yes, search that half; otherwise, search the other half
     *
     * How to identify sorted half:
     * - If nums[left] <= nums[mid], LEFT half is sorted
     * - Otherwise, RIGHT half is sorted
     *
     * Example: [4,5,6,7,0,1,2], target = 0
     * - mid = 7, left half [4,5,6,7] is sorted
     * - 0 is NOT in [4,7], so search right half [0,1,2]
     *
     * Time: O(log n)
     * Space: O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                // Is target in the sorted left half?
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;  // Search left half
                } else {
                    left = mid + 1;   // Search right half
                }
            }
            // Right half is sorted
            else {
                // Is target in the sorted right half?
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;   // Search right half
                } else {
                    right = mid - 1;  // Search left half
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchRotatedSortedArray solution = new SearchRotatedSortedArray();

        // Test case 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array: [4,5,6,7,0,1,2], Target: 0");
        System.out.println("Index: " + solution.search(nums1, 0));
        // Expected: 4

        // Test case 2
        System.out.println("\nArray: [4,5,6,7,0,1,2], Target: 3");
        System.out.println("Index: " + solution.search(nums1, 3));
        // Expected: -1

        // Test case 3
        int[] nums2 = {1};
        System.out.println("\nArray: [1], Target: 0");
        System.out.println("Index: " + solution.search(nums2, 0));
        // Expected: -1

        // Test case 4
        int[] nums3 = {3, 1};
        System.out.println("\nArray: [3,1], Target: 1");
        System.out.println("Index: " + solution.search(nums3, 1));
        // Expected: 1
    }
}
