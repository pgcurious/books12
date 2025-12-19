package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #76 - Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Difficulty: Hard
 *
 * Problem: Given strings s and t, return the minimum window in s which
 * contains all characters from t. If no such window exists, return "".
 *
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 */
public class MinimumWindowSubstring {

    /**
     * INTUITION:
     * -----------
     * Classic sliding window with a twist - we need to track character counts.
     *
     * Approach:
     * 1. Count all characters needed from t
     * 2. Expand window (right pointer) until we have all characters
     * 3. Contract window (left pointer) to find minimum
     * 4. Repeat until we've scanned the entire string
     *
     * Key variables:
     * - need: Map of characters we need and their counts
     * - have: How many unique characters we have with correct count
     * - required: Total unique characters we need
     *
     * Time: O(|s| + |t|)
     * Space: O(|s| + |t|)
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Count characters needed from t
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Count characters in current window
        Map<Character, Integer> window = new HashMap<>();

        int required = need.size();  // Unique chars needed
        int have = 0;                 // Unique chars satisfied

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add character to window
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Check if this character satisfies a requirement
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                have++;
            }

            // Try to contract window while we have all characters
            while (have == required) {
                // Update minimum if this window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                // Remove leftmost character
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);

                // Check if we no longer satisfy a requirement
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    have--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        // Test case 1
        System.out.println("s = 'ADOBECODEBANC', t = 'ABC'");
        System.out.println("Output: '" + solution.minWindow("ADOBECODEBANC", "ABC") + "'");
        // Expected: "BANC"

        // Test case 2
        System.out.println("\ns = 'a', t = 'a'");
        System.out.println("Output: '" + solution.minWindow("a", "a") + "'");
        // Expected: "a"

        // Test case 3
        System.out.println("\ns = 'a', t = 'aa'");
        System.out.println("Output: '" + solution.minWindow("a", "aa") + "'");
        // Expected: "" (not possible)
    }
}
