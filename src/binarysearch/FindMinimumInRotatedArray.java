package binarysearch;

/**
 * LeetCode #153 - Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Difficulty: Medium
 *
 * Problem: A sorted array was rotated. Find the minimum element.
 * (Array has no duplicates)
 *
 * Example:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * (Original: [1,2,3,4,5] rotated at index 3)
 */
public class FindMinimumInRotatedArray {

    /**
     * INTUITION:
     * -----------
     * In a rotated sorted array, the minimum is the "pivot point"
     * where the rotation occurred.
     *
     * Key insight: Compare mid with RIGHT element (not left!)
     *
     * If nums[mid] > nums[right]:
     * - The minimum is in the RIGHT half (after mid)
     * - Example: [3,4,5,1,2] mid=5, right=2, min is in [1,2]
     *
     * If nums[mid] <= nums[right]:
     * - The minimum is in the LEFT half (including mid)
     * - Example: [2,3,4,5,1] mid=4, right=1... wait, that doesn't work
     * - Actually: [5,1,2,3,4] mid=2, right=4, min could be mid or left
     *
     * Why compare with right not left?
     * - If we compare with left and both halves are sorted differently,
     *   we can't determine which half has the minimum.
     *
     * Time: O(log n)
     * Space: O(1)
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // If array is not rotated (or rotated n times)
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid > right, minimum is in right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // If mid <= right, minimum is in left half (including mid)
            else {
                right = mid;
            }
        }

        return nums[left];
    }

    /**
     * Alternative: Find the inflection point
     */
    public int findMinAlt(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost, min is to the right
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedArray solution = new FindMinimumInRotatedArray();

        // Test case 1
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Array: [3,4,5,1,2]");
        System.out.println("Minimum: " + solution.findMin(nums1));
        // Expected: 1

        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("\nArray: [4,5,6,7,0,1,2]");
        System.out.println("Minimum: " + solution.findMin(nums2));
        // Expected: 0

        // Test case 3
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("\nArray: [11,13,15,17] (not rotated)");
        System.out.println("Minimum: " + solution.findMin(nums3));
        // Expected: 11

        // Test case 4
        int[] nums4 = {2, 1};
        System.out.println("\nArray: [2,1]");
        System.out.println("Minimum: " + solution.findMin(nums4));
        // Expected: 1
    }
}
