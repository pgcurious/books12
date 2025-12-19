package arrays;

import java.util.*;

/**
 * LeetCode #49 - Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Difficulty: Medium
 *
 * Problem: Given an array of strings, group anagrams together.
 *
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {

    /**
     * INTUITION:
     * -----------
     * Anagrams have the same characters in different orders.
     * If we SORT each word, all anagrams become the same string!
     *
     * "eat" -> "aet"
     * "tea" -> "aet"
     * "ate" -> "aet"
     *
     * So sorted string is the perfect KEY for grouping anagrams.
     *
     * Approach:
     * 1. Create a HashMap: sorted_string -> list of original strings
     * 2. For each word, sort it and add original to that group
     * 3. Return all groups
     *
     * Time: O(n * k log k) where n = number of strings, k = max string length
     * Space: O(n * k) for storing all strings
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Map: sorted string -> list of anagrams
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // Sort the string to get the key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Add to the group (create list if first in group)
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(groups.values());
    }

    /**
     * Alternative: Use character count as key (avoids sorting)
     * Time: O(n * k) - better for long strings
     *
     * Key format: "#2#1#0#0..." representing count of each letter
     */
    public List<List<String>> groupAnagramsCount(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // Count characters
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            // Build key from counts
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                keyBuilder.append('#').append(count[i]);
            }
            String key = keyBuilder.toString();

            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();

        // Test case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Input: " + Arrays.toString(strs1));
        System.out.println("Output: " + solution.groupAnagrams(strs1));
        // Expected: [["bat"],["nat","tan"],["ate","eat","tea"]] (order may vary)

        // Test case 2
        String[] strs2 = {""};
        System.out.println("\nInput: " + Arrays.toString(strs2));
        System.out.println("Output: " + solution.groupAnagrams(strs2));
        // Expected: [[""]]

        // Test case 3
        String[] strs3 = {"a"};
        System.out.println("\nInput: " + Arrays.toString(strs3));
        System.out.println("Output: " + solution.groupAnagrams(strs3));
        // Expected: [["a"]]
    }
}
