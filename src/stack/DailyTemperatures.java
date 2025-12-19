package stack;

import java.util.*;

/**
 * LeetCode #739 - Daily Temperatures
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Difficulty: Medium
 *
 * Problem: Given an array of daily temperatures, return an array where
 * answer[i] is the number of days you have to wait until a warmer temperature.
 * If there is no future day with warmer temperature, put 0.
 *
 * Example:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Explanation: For temp 73 at day 0, next warmer is 74 at day 1 (1 day wait)
 */
public class DailyTemperatures {

    /**
     * INTUITION:
     * -----------
     * We need to find the NEXT GREATER element for each position.
     * This is a classic "Monotonic Stack" problem!
     *
     * Approach:
     * - Use stack to store INDICES of temperatures waiting for a warmer day
     * - Stack maintains decreasing temperatures (monotonic decreasing)
     * - When we find a warmer temperature, pop all smaller ones and calculate wait
     *
     * Why this works:
     * - Stack keeps track of days still waiting for warmer weather
     * - When a warmer day arrives, we resolve all waiting days that are colder
     *
     * Time: O(n) - each element pushed and popped at most once
     * Space: O(n) - for the stack
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        // Stack stores INDICES (not temperatures)
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop all days that found a warmer day (today)
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = i - prevDay;  // Days waited
            }

            stack.push(i);  // Current day waits for warmer
        }

        // Remaining in stack have no warmer day (already initialized to 0)
        return result;
    }

    /**
     * Alternative: Process from right to left
     */
    public int[] dailyTemperaturesReverse(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();  // Stores indices

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop colder or equal temperatures (they won't help)
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            // If stack not empty, top is the next warmer day
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();

        // Test case 1
        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Temperatures: " + Arrays.toString(temps1));
        System.out.println("Wait days: " + Arrays.toString(solution.dailyTemperatures(temps1)));
        // Expected: [1, 1, 4, 2, 1, 1, 0, 0]

        // Test case 2
        int[] temps2 = {30, 40, 50, 60};
        System.out.println("\nTemperatures: " + Arrays.toString(temps2));
        System.out.println("Wait days: " + Arrays.toString(solution.dailyTemperatures(temps2)));
        // Expected: [1, 1, 1, 0]

        // Test case 3
        int[] temps3 = {30, 60, 90};
        System.out.println("\nTemperatures: " + Arrays.toString(temps3));
        System.out.println("Wait days: " + Arrays.toString(solution.dailyTemperatures(temps3)));
        // Expected: [1, 1, 0]
    }
}
