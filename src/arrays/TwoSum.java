package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * LeetCode #1 - Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Difficulty: Easy
 *
 * Problem: Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1] (because nums[0] + nums[1] = 2 + 7 = 9)
 */
public class TwoSum {

    /**
     * INTUITION:
     * -----------
     * Brute force would check every pair - O(n²). But we can do better!
     *
     * Key insight: If we need a + b = target, then b = target - a
     *
     * So for each number, we just need to check if (target - number) exists.
     * Use a HashMap to store numbers we've seen with their indices.
     *
     * As we iterate:
     * 1. Calculate complement = target - current number
     * 2. If complement exists in map, we found our pair!
     * 3. Otherwise, store current number in map for future lookups
     *
     * Time: O(n) - single pass through array
     * Space: O(n) - HashMap storage
     */
    public int[] twoSum(int[] nums, int target) {
        // Map: number -> its index
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if we've seen the complement before
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }

            // Store current number with its index
            seen.put(nums[i], i);
        }

        return new int[] {}; // No solution found
    }

    // Test the solution
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        // Test case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Input: " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums1, target1)));
        // Expected: [0, 1]

        // Test case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("\nInput: " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums2, target2)));
        // Expected: [1, 2]

        // Test case 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("\nInput: " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(solution.twoSum(nums3, target3)));
        // Expected: [0, 1]
    }
}
