package dp;

import java.util.Arrays;

/**
 * LeetCode #300 - Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Difficulty: Medium
 *
 * Problem: Given an integer array nums, return the length of the
 * longest strictly increasing subsequence.
 *
 * Subsequence = sequence derived by deleting some or no elements
 * without changing the order of remaining elements.
 *
 * Example:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4 ([2,3,7,101] or [2,3,7,18])
 */
public class LongestIncreasingSubsequence {

    /**
     * INTUITION (DP approach - O(n²)):
     * ---------------------------------
     * dp[i] = length of LIS ending at index i
     *
     * For each element, check all previous elements.
     * If nums[j] < nums[i], we can extend the LIS ending at j.
     *
     * dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]
     *
     * Base case: dp[i] = 1 (each element is an LIS of length 1)
     *
     * Time: O(n²)
     * Space: O(n)
     */
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);  // Each element is an LIS of length 1

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If nums[j] < nums[i], we can extend LIS ending at j
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * INTUITION (Binary Search - O(n log n)):
     * ----------------------------------------
     * Maintain an array 'tails' where tails[i] is the smallest tail
     * element for an LIS of length i+1.
     *
     * For each number:
     * - If larger than all tails, extend the longest LIS
     * - Otherwise, replace the first tail >= num (keeps tails optimal)
     *
     * The array 'tails' is always sorted, so we can use binary search!
     *
     * Example: [10,9,2,5,3,7,101,18]
     * tails evolution:
     * [10] -> [9] -> [2] -> [2,5] -> [2,3] -> [2,3,7] -> [2,3,7,101] -> [2,3,7,18]
     *
     * Time: O(n log n)
     * Space: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;  // Current length of LIS

        for (int num : nums) {
            // Binary search for position of num in tails
            int left = 0, right = size;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // left is the position where num should go
            tails[left] = num;

            // If num is larger than all elements, extend LIS
            if (left == size) {
                size++;
            }
        }

        return size;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

        // Test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("LIS length: " + solution.lengthOfLIS(nums1));
        // Expected: 4 ([2,3,7,101] or [2,3,7,18])

        // Test case 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("\nInput: " + Arrays.toString(nums2));
        System.out.println("LIS length: " + solution.lengthOfLIS(nums2));
        // Expected: 4 ([0,1,2,3])

        // Test case 3
        int[] nums3 = {7, 7, 7, 7, 7};
        System.out.println("\nInput: " + Arrays.toString(nums3));
        System.out.println("LIS length: " + solution.lengthOfLIS(nums3));
        // Expected: 1 (strictly increasing, so only one 7)
    }
}
