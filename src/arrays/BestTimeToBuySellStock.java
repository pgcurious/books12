package arrays;

/**
 * LeetCode #121 - Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Difficulty: Easy
 *
 * Problem: Given array prices where prices[i] is the stock price on day i.
 * Maximize profit by choosing ONE day to buy and ONE future day to sell.
 * Return the maximum profit (or 0 if no profit possible).
 *
 * Example:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5 (Buy on day 2 at price=1, sell on day 5 at price=6, profit=5)
 */
public class BestTimeToBuySellStock {

    /**
     * INTUITION:
     * -----------
     * We need to find the maximum difference where the larger number comes after
     * the smaller number. This is finding: max(prices[j] - prices[i]) where j > i
     *
     * Brute force checks every pair - O(n²). But we can do better!
     *
     * Key insight: As we scan left to right, we only care about:
     * 1. The minimum price we've seen so far (best buy price)
     * 2. The maximum profit if we sold today
     *
     * At each day, we update:
     * - minPrice = minimum of (current minPrice, today's price)
     * - maxProfit = maximum of (current maxProfit, today's price - minPrice)
     *
     * Time: O(n) - single pass
     * Space: O(1) - just two variables
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;  // Best buy price so far
        int maxProfit = 0;                  // Best profit so far

        for (int price : prices) {
            // Update minimum price seen so far
            if (price < minPrice) {
                minPrice = price;
            }

            // Calculate profit if we sold today
            int profitToday = price - minPrice;

            // Update max profit if this is better
            if (profitToday > maxProfit) {
                maxProfit = profitToday;
            }
        }

        return maxProfit;
    }

    /**
     * Cleaner version using Math.min/max
     */
    public int maxProfitClean(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuySellStock solution = new BestTimeToBuySellStock();

        // Test case 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Prices: [7,1,5,3,6,4]");
        System.out.println("Max profit: " + solution.maxProfit(prices1));
        // Expected: 5 (buy at 1, sell at 6)

        // Test case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("\nPrices: [7,6,4,3,1]");
        System.out.println("Max profit: " + solution.maxProfit(prices2));
        // Expected: 0 (prices only decrease)

        // Test case 3
        int[] prices3 = {2, 4, 1};
        System.out.println("\nPrices: [2,4,1]");
        System.out.println("Max profit: " + solution.maxProfit(prices3));
        // Expected: 2 (buy at 2, sell at 4)
    }
}
