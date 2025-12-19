package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #242 - Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 *
 * Difficulty: Easy
 *
 * Problem: Given two strings s and t, return true if t is an anagram of s.
 * An anagram uses the exact same letters with the same frequency.
 *
 * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 */
public class ValidAnagram {

    /**
     * INTUITION:
     * -----------
     * An anagram has the exact same characters, just rearranged.
     * So we need to check if both strings have identical character frequencies.
     *
     * Approach 1: Sorting - O(n log n)
     * If we sort both strings, anagrams become identical.
     *
     * Approach 2: Frequency Count - O(n) [BETTER]
     * Count characters in first string, subtract for second.
     * If all counts are zero, it's an anagram.
     *
     * Since we only have lowercase letters (26), we can use an array
     * instead of HashMap for constant space optimization.
     *
     * Time: O(n)
     * Space: O(1) - fixed size array of 26
     */
    public boolean isAnagram(String s, String t) {
        // Quick check: different lengths can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Count frequency of each character (a-z)
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;  // Add for s
            count[t.charAt(i) - 'a']--;  // Subtract for t
        }

        // If anagram, all counts should be 0
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Alternative: Using HashMap (supports Unicode)
     */
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> count = new HashMap<>();

        // Count characters in s
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Subtract characters in t
        for (char c : t.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) - 1);
            if (count.get(c) < 0) {
                return false; // More of this char in t than s
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();

        // Test case 1
        System.out.println("'anagram' vs 'nagaram': " + solution.isAnagram("anagram", "nagaram"));
        // Expected: true

        // Test case 2
        System.out.println("'rat' vs 'car': " + solution.isAnagram("rat", "car"));
        // Expected: false

        // Test case 3
        System.out.println("'listen' vs 'silent': " + solution.isAnagram("listen", "silent"));
        // Expected: true
    }
}
