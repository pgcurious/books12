package dp;

import java.util.Arrays;

/**
 * LeetCode #322 - Coin Change
 * https://leetcode.com/problems/coin-change/
 *
 * Difficulty: Medium
 *
 * Problem: Given coins of different denominations and a total amount,
 * find the fewest number of coins needed to make up that amount.
 * Return -1 if it's impossible.
 *
 * Example:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3 (5 + 5 + 1 = 11)
 */
public class CoinChange {

    /**
     * INTUITION:
     * -----------
     * This is the classic "Unbounded Knapsack" variation.
     *
     * For each amount, we try every coin and pick the one that
     * gives minimum coins.
     *
     * dp[i] = minimum coins needed to make amount i
     *
     * For each coin c, if we use it:
     *   dp[i] = min(dp[i], dp[i - c] + 1)
     *   (one coin c + however many coins needed for remaining amount)
     *
     * Base case: dp[0] = 0 (zero coins for amount 0)
     *
     * Time: O(amount * coins)
     * Space: O(amount)
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i] = min coins to make amount i
        int[] dp = new int[amount + 1];

        // Initialize with impossible value (amount+1 is larger than any valid answer)
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;  // 0 coins needed for amount 0

        // For each amount from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                if (coin <= i) {
                    // Use this coin: 1 + coins needed for remaining
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If still impossible, return -1
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Alternative: Build up understanding step by step
     */
    public int coinChangeVerbose(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // Can we use this coin?
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    // Take minimum of:
                    // - current best for amount i
                    // - using this coin + best for remaining amount
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();

        // Test case 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Coins: [1,2,5], Amount: 11");
        System.out.println("Min coins: " + solution.coinChange(coins1, amount1));
        // Expected: 3 (5 + 5 + 1)

        // Test case 2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("\nCoins: [2], Amount: 3");
        System.out.println("Min coins: " + solution.coinChange(coins2, amount2));
        // Expected: -1 (impossible)

        // Test case 3
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("\nCoins: [1], Amount: 0");
        System.out.println("Min coins: " + solution.coinChange(coins3, amount3));
        // Expected: 0

        // Test case 4
        int[] coins4 = {1, 5, 10, 25};
        int amount4 = 30;
        System.out.println("\nCoins: [1,5,10,25], Amount: 30");
        System.out.println("Min coins: " + solution.coinChange(coins4, amount4));
        // Expected: 2 (25 + 5)
    }
}
