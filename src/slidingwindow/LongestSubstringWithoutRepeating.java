package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode #3 - Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Difficulty: Medium
 *
 * Problem: Given a string s, find the length of the longest substring
 * without repeating characters.
 *
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3 (The answer is "abc")
 */
public class LongestSubstringWithoutRepeating {

    /**
     * INTUITION:
     * -----------
     * We need a CONTIGUOUS sequence with all unique characters.
     * This screams "Sliding Window"!
     *
     * Approach:
     * - Maintain a window [left, right] containing unique characters
     * - Expand right to include new characters
     * - When we find a duplicate, shrink from left until unique again
     * - Track the maximum window size
     *
     * Two implementations:
     * 1. HashSet - simple, shrink left one by one
     * 2. HashMap - optimal, jump left directly to after duplicate
     *
     * Time: O(n) - each character visited at most twice
     * Space: O(min(m, n)) where m is charset size
     */
    public int lengthOfLongestSubstring(String s) {
        // Map: character -> its most recent index
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If we've seen this character, move left past its last occurrence
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }

            // Update the character's position
            lastSeen.put(c, right);

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Alternative: Using HashSet (simpler but slightly less optimal)
     */
    public int lengthOfLongestSubstringSet(String s) {
        Set<Character> window = new HashSet<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Shrink window until character is unique
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(c);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

        // Test case 1
        System.out.println("Input: 'abcabcbb'");
        System.out.println("Output: " + solution.lengthOfLongestSubstring("abcabcbb"));
        // Expected: 3 ("abc")

        // Test case 2
        System.out.println("\nInput: 'bbbbb'");
        System.out.println("Output: " + solution.lengthOfLongestSubstring("bbbbb"));
        // Expected: 1 ("b")

        // Test case 3
        System.out.println("\nInput: 'pwwkew'");
        System.out.println("Output: " + solution.lengthOfLongestSubstring("pwwkew"));
        // Expected: 3 ("wke")

        // Test case 4
        System.out.println("\nInput: ''");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(""));
        // Expected: 0
    }
}
