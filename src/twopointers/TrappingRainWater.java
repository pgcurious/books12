package twopointers;

/**
 * LeetCode #42 - Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Difficulty: Hard
 *
 * Problem: Given n non-negative integers representing elevation map bars,
 * compute how much water it can trap after raining.
 *
 * Example:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    /**
     * INTUITION:
     * -----------
     * Water above each bar = min(maxLeft, maxRight) - height[i]
     *
     * The water level at any position is determined by the SHORTER
     * of the two tallest walls on either side.
     *
     * Approach 1: Precompute (easier to understand)
     * - leftMax[i] = max height from 0 to i
     * - rightMax[i] = max height from i to n-1
     * - Water at i = min(leftMax[i], rightMax[i]) - height[i]
     *
     * Approach 2: Two Pointers (optimal)
     * - Use two pointers from both ends
     * - Track leftMax and rightMax as we go
     * - Process the side with smaller max (that's the limiting factor)
     *
     * Time: O(n)
     * Space: O(1) for two-pointer approach
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            // Update max heights
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // Process the side with smaller max (it's the limiting factor)
            if (leftMax < rightMax) {
                // Water at left is bounded by leftMax
                totalWater += leftMax - height[left];
                left++;
            } else {
                // Water at right is bounded by rightMax
                totalWater += rightMax - height[right];
                right--;
            }
        }

        return totalWater;
    }

    /**
     * Alternative: Precompute approach (easier to understand)
     * Time: O(n), Space: O(n)
     */
    public int trapPrecompute(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];   // Max height from left up to i
        int[] rightMax = new int[n];  // Max height from right up to i

        // Build leftMax
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Build rightMax
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate water at each position
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            totalWater += waterLevel - height[i];
        }

        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        // Test case 1
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Heights: [0,1,0,2,1,0,1,3,2,1,2,1]");
        System.out.println("Water trapped: " + solution.trap(height1));
        // Expected: 6

        // Test case 2
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("\nHeights: [4,2,0,3,2,5]");
        System.out.println("Water trapped: " + solution.trap(height2));
        // Expected: 9
    }
}
