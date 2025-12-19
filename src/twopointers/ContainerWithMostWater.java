package twopointers;

/**
 * LeetCode #11 - Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Difficulty: Medium
 *
 * Problem: Given n non-negative integers representing heights of vertical lines,
 * find two lines that together with x-axis forms a container holding the most water.
 *
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: Lines at index 1 (height=8) and index 8 (height=7)
 *              Area = min(8,7) * (8-1) = 7 * 7 = 49
 */
public class ContainerWithMostWater {

    /**
     * INTUITION:
     * -----------
     * Brute force: Check every pair - O(n²). Too slow!
     *
     * Key insight: Container area = min(left, right) * width
     *
     * Two pointer approach:
     * Start with widest container (left=0, right=n-1), then shrink inward.
     *
     * Which pointer to move? The SHORTER one!
     * Why? If we move the taller one:
     * - Width decreases by 1
     * - Height is still limited by the shorter one
     * - Area can only decrease or stay same
     *
     * If we move the shorter one:
     * - Width decreases by 1
     * - But we MIGHT find a taller line
     * - Area could potentially increase!
     *
     * Time: O(n) - single pass
     * Space: O(1)
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Calculate current area
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;

            maxArea = Math.max(maxArea, area);

            // Move the shorter line (it's the limiting factor)
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test case 1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Heights: [1,8,6,2,5,4,8,3,7]");
        System.out.println("Max area: " + solution.maxArea(height1));
        // Expected: 49 (between index 1 and 8)

        // Test case 2
        int[] height2 = {1, 1};
        System.out.println("\nHeights: [1,1]");
        System.out.println("Max area: " + solution.maxArea(height2));
        // Expected: 1

        // Test case 3
        int[] height3 = {4, 3, 2, 1, 4};
        System.out.println("\nHeights: [4,3,2,1,4]");
        System.out.println("Max area: " + solution.maxArea(height3));
        // Expected: 16 (between index 0 and 4)
    }
}
