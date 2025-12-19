package dp;

/**
 * LeetCode #70 - Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 *
 * Difficulty: Easy
 *
 * Problem: You can climb 1 or 2 steps at a time. How many distinct ways
 * can you climb to the top (n steps)?
 *
 * Example:
 * Input: n = 3
 * Output: 3 (1+1+1, 1+2, 2+1)
 */
public class ClimbingStairs {

    /**
     * INTUITION:
     * -----------
     * This is the Fibonacci sequence in disguise!
     *
     * To reach step n, you can come from:
     * - Step n-1 (take 1 step)
     * - Step n-2 (take 2 steps)
     *
     * So: ways(n) = ways(n-1) + ways(n-2)
     *
     * Base cases:
     * - ways(1) = 1 (only one way: take 1 step)
     * - ways(2) = 2 (1+1 or 2)
     *
     * This is exactly Fibonacci!
     *
     * Approaches:
     * 1. Recursion with memoization - O(n) time, O(n) space
     * 2. Bottom-up DP array - O(n) time, O(n) space
     * 3. Optimized (only track last 2) - O(n) time, O(1) space
     */

    /**
     * Approach 1: Top-down with memoization
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return climbHelper(n, memo);
    }

    private int climbHelper(int n, int[] memo) {
        if (n <= 2) return n;
        if (memo[n] != 0) return memo[n];

        memo[n] = climbHelper(n - 1, memo) + climbHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Approach 2: Bottom-up DP array
     */
    public int climbStairsDP(int n) {
        if (n <= 2) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Approach 3: Optimized O(1) space
     * Only need to track last 2 values
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int prev2 = 1;  // ways(n-2)
        int prev1 = 2;  // ways(n-1)

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        System.out.println("n=2: " + solution.climbStairs(2) + " ways");
        // Expected: 2 (1+1, 2)

        System.out.println("n=3: " + solution.climbStairs(3) + " ways");
        // Expected: 3 (1+1+1, 1+2, 2+1)

        System.out.println("n=5: " + solution.climbStairs(5) + " ways");
        // Expected: 8

        System.out.println("n=10: " + solution.climbStairs(10) + " ways");
        // Expected: 89
    }
}
