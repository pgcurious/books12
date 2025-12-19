package arrays;

import java.util.Arrays;

/**
 * LeetCode #238 - Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Difficulty: Medium
 *
 * Problem: Given an integer array nums, return an array answer such that
 * answer[i] is equal to the product of all elements except nums[i].
 * Must run in O(n) time WITHOUT using division.
 *
 * Example:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Explanation: [2*3*4, 1*3*4, 1*2*4, 1*2*3]
 */
public class ProductExceptSelf {

    /**
     * INTUITION:
     * -----------
     * Without division, we can't just compute total product and divide.
     *
     * Key insight: For any position i, the answer is:
     * (product of all elements to the LEFT) * (product of all elements to the RIGHT)
     *
     * Strategy:
     * 1. First pass (left to right): Build prefix products
     *    - prefix[i] = product of all elements before index i
     *
     * 2. Second pass (right to left): Multiply by suffix products
     *    - suffix[i] = product of all elements after index i
     *
     * Example: [1, 2, 3, 4]
     * - Prefix products: [1, 1, 2, 6]      (nothing before, 1, 1*2, 1*2*3)
     * - Suffix products: [24, 12, 4, 1]    (2*3*4, 3*4, 4, nothing after)
     * - Result: prefix * suffix = [24, 12, 8, 6]
     *
     * Time: O(n) - two passes
     * Space: O(1) - only output array (not counting output as extra space)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // First pass: Calculate prefix products (products of all elements to the left)
        // result[i] will hold product of all elements before i
        result[0] = 1; // Nothing to the left of first element
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // After this: result = [1, 1, 2, 6] for input [1,2,3,4]

        // Second pass: Multiply by suffix products (products of all elements to the right)
        int suffixProduct = 1; // Nothing to the right of last element
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        // After this: result = [24, 12, 8, 6]

        return result;
    }

    /**
     * More explicit version with separate prefix and suffix arrays
     * (uses O(n) extra space but easier to understand)
     */
    public int[] productExceptSelfExplicit(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];  // prefix[i] = product of nums[0..i-1]
        int[] suffix = new int[n];  // suffix[i] = product of nums[i+1..n-1]
        int[] result = new int[n];

        // Build prefix products
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // Build suffix products
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        // Combine
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    public static void main(String[] args) {
        ProductExceptSelf solution = new ProductExceptSelf();

        // Test case 1
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + Arrays.toString(solution.productExceptSelf(nums1)));
        // Expected: [24, 12, 8, 6]

        // Test case 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println("\nInput: " + Arrays.toString(nums2));
        System.out.println("Output: " + Arrays.toString(solution.productExceptSelf(nums2)));
        // Expected: [0, 0, 9, 0, 0]

        // Test case 3
        int[] nums3 = {2, 3};
        System.out.println("\nInput: " + Arrays.toString(nums3));
        System.out.println("Output: " + Arrays.toString(solution.productExceptSelf(nums3)));
        // Expected: [3, 2]
    }
}
