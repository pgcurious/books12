package twopointers;

import java.util.*;

/**
 * LeetCode #15 - 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * Difficulty: Medium
 *
 * Problem: Given an array nums, find all unique triplets [a, b, c]
 * such that a + b + c = 0.
 *
 * Example:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 */
public class ThreeSum {

    /**
     * INTUITION:
     * -----------
     * This is an extension of Two Sum, but we need to find THREE numbers.
     *
     * Approach:
     * 1. Sort the array first (enables two-pointer technique)
     * 2. Fix one number (a), then find two numbers (b, c) that sum to -a
     * 3. Use two pointers for the remaining two numbers (like Two Sum II)
     *
     * Key steps:
     * - For each number nums[i], we need: nums[j] + nums[k] = -nums[i]
     * - This becomes a Two Sum problem on the sorted array!
     * - Skip duplicates to avoid duplicate triplets
     *
     * Time: O(n²) - O(n log n) for sort + O(n²) for nested loop
     * Space: O(1) or O(n) depending on sorting implementation
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Sort first - enables two-pointer technique
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Early termination: if smallest number > 0, no solution possible
            if (nums[i] > 0) {
                break;
            }

            // Two pointer approach for remaining two numbers
            int target = -nums[i];  // We need j + k = -nums[i]
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    // Found a triplet!
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for second number
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for third number
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;   // Need larger sum
                } else {
                    right--;  // Need smaller sum
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        // Test case 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: [-1,0,1,2,-1,-4]");
        System.out.println("Output: " + solution.threeSum(nums1));
        // Expected: [[-1,-1,2],[-1,0,1]]

        // Test case 2
        int[] nums2 = {0, 1, 1};
        System.out.println("\nInput: [0,1,1]");
        System.out.println("Output: " + solution.threeSum(nums2));
        // Expected: []

        // Test case 3
        int[] nums3 = {0, 0, 0};
        System.out.println("\nInput: [0,0,0]");
        System.out.println("Output: " + solution.threeSum(nums3));
        // Expected: [[0,0,0]]
    }
}
