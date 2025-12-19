package slidingwindow;

/**
 * LeetCode #53 - Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Difficulty: Medium
 *
 * Problem: Given an integer array nums, find the contiguous subarray
 * with the largest sum and return its sum.
 *
 * Example:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6 (subarray [4,-1,2,1])
 */
public class MaximumSubarray {

    /**
     * INTUITION (Kadane's Algorithm):
     * --------------------------------
     * At each position, we decide: Should we extend the previous subarray
     * or start fresh from here?
     *
     * Key insight:
     * - If previous sum is positive, it helps us (add current to it)
     * - If previous sum is negative, it hurts us (start fresh)
     *
     * currentSum = max(nums[i], currentSum + nums[i])
     *
     * Translation: "Is it better to start fresh or continue?"
     * - If currentSum + nums[i] < nums[i], then currentSum was negative
     *   and we should start fresh
     *
     * Time: O(n)
     * Space: O(1)
     */
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];  // Sum of current subarray
        int maxSum = nums[0];      // Best sum found so far

        for (int i = 1; i < nums.length; i++) {
            // Extend previous subarray or start new one?
            // If currentSum is negative, start fresh with nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * Alternative: More explicit version
     */
    public int maxSubArrayExplicit(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int num : nums) {
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);

            // If sum goes negative, reset (start fresh)
            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        return maxSum;
    }

    /**
     * Bonus: Return the actual subarray (not just sum)
     */
    public int[] maxSubArrayWithIndices(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        int start = 0, end = 0;     // Result indices
        int tempStart = 0;           // Potential start of new subarray

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                // Start fresh
                currentSum = nums[i];
                tempStart = i;
            } else {
                // Extend
                currentSum = currentSum + nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new int[] {start, end, maxSum};
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();

        // Test case 1
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input: [-2,1,-3,4,-1,2,1,-5,4]");
        System.out.println("Max subarray sum: " + solution.maxSubArray(nums1));
        // Expected: 6 (subarray [4,-1,2,1])

        // Test case 2
        int[] nums2 = {1};
        System.out.println("\nInput: [1]");
        System.out.println("Max subarray sum: " + solution.maxSubArray(nums2));
        // Expected: 1

        // Test case 3
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("\nInput: [5,4,-1,7,8]");
        System.out.println("Max subarray sum: " + solution.maxSubArray(nums3));
        // Expected: 23

        // Test with indices
        int[] result = solution.maxSubArrayWithIndices(nums1);
        System.out.println("\nBonus - Subarray indices: [" + result[0] + ", " + result[1] + "], sum = " + result[2]);
    }
}
