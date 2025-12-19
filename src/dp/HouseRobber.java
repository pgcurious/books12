package dp;

import java.util.Arrays;

/**
 * LeetCode #198 - House Robber
 * https://leetcode.com/problems/house-robber/
 *
 * Difficulty: Medium
 *
 * Problem: You're a robber planning to rob houses along a street.
 * Each house has a certain amount of money. Adjacent houses have security
 * systems connected - if two adjacent houses are robbed, police will be alerted.
 * Find the maximum amount you can rob without triggering the alarm.
 *
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: 4 (Rob house 0 and house 2: 1 + 3 = 4)
 */
public class HouseRobber {

    /**
     * INTUITION:
     * -----------
     * At each house, we have 2 choices:
     * 1. ROB this house: Get its money + max from house i-2 (skip adjacent)
     * 2. SKIP this house: Take max from house i-1
     *
     * Recurrence: dp[i] = max(nums[i] + dp[i-2], dp[i-1])
     *
     * Translation: "Should I rob this house or skip it?"
     *
     * Base cases:
     * - dp[0] = nums[0] (only one house, rob it)
     * - dp[1] = max(nums[0], nums[1]) (two houses, pick richer one)
     *
     * Time: O(n)
     * Space: O(1) with optimization
     */

    /**
     * Approach 1: DP array (easier to understand)
     */
    public int robDP(int[] nums) {
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // Rob this house OR skip it
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    /**
     * Approach 2: Space optimized O(1)
     * Only need last 2 values
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int prev2 = nums[0];                    // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Alternative: Using clear variable names
     */
    public int robClear(int[] nums) {
        int robPrevPrev = 0;  // Max if we robbed up to i-2
        int robPrev = 0;      // Max if we robbed up to i-1

        for (int num : nums) {
            // Max we can get including current house
            int robCurrent = Math.max(robPrev, robPrevPrev + num);
            robPrevPrev = robPrev;
            robPrev = robCurrent;
        }

        return robPrev;
    }

    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();

        // Test case 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Houses: " + Arrays.toString(nums1));
        System.out.println("Max robbery: " + solution.rob(nums1));
        // Expected: 4 (rob house 0 and 2)

        // Test case 2
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("\nHouses: " + Arrays.toString(nums2));
        System.out.println("Max robbery: " + solution.rob(nums2));
        // Expected: 12 (rob house 0, 2, and 4: 2 + 9 + 1 = 12)

        // Test case 3
        int[] nums3 = {2, 1, 1, 2};
        System.out.println("\nHouses: " + Arrays.toString(nums3));
        System.out.println("Max robbery: " + solution.rob(nums3));
        // Expected: 4 (rob house 0 and 3)
    }
}
