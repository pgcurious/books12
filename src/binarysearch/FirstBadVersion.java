package binarysearch;

/**
 * LeetCode #278 - First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 *
 * Difficulty: Easy
 *
 * Problem: You have n versions [1, 2, ..., n] and want to find the first bad one.
 * You have an API isBadVersion(version) that returns whether version is bad.
 * Minimize the number of API calls.
 *
 * Example:
 * Input: n = 5, bad = 4
 * Versions: [1, 2, 3, 4, 5]
 *            G  G  G  B  B
 * Output: 4 (first bad version)
 */
public class FirstBadVersion {

    // This would be provided by LeetCode
    private int badVersion;

    public FirstBadVersion(int badVersion) {
        this.badVersion = badVersion;
    }

    boolean isBadVersion(int version) {
        return version >= badVersion;
    }

    /**
     * INTUITION:
     * -----------
     * The versions look like: G G G G B B B B
     * We need to find the FIRST bad version (leftmost B).
     *
     * This is a "find leftmost true" binary search pattern!
     *
     * When we find a bad version:
     * - It MIGHT be the first bad, or first bad is to the left
     * - So we keep searching LEFT (right = mid)
     *
     * When we find a good version:
     * - First bad must be to the right
     * - So we search RIGHT (left = mid + 1)
     *
     * Why right = mid instead of mid - 1?
     * - Because mid might BE the answer, we can't exclude it!
     *
     * Time: O(log n)
     * Space: O(1)
     */
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                // mid might be first bad, or first bad is to the left
                right = mid;  // Keep mid in search range
            } else {
                // mid is good, first bad must be after
                left = mid + 1;
            }
        }

        // left == right, pointing to first bad version
        return left;
    }

    public static void main(String[] args) {
        // Test case 1
        FirstBadVersion solution1 = new FirstBadVersion(4);
        System.out.println("n=5, first bad=4");
        System.out.println("Found: " + solution1.firstBadVersion(5));
        // Expected: 4

        // Test case 2
        FirstBadVersion solution2 = new FirstBadVersion(1);
        System.out.println("\nn=5, first bad=1");
        System.out.println("Found: " + solution2.firstBadVersion(5));
        // Expected: 1

        // Test case 3
        FirstBadVersion solution3 = new FirstBadVersion(1);
        System.out.println("\nn=1, first bad=1");
        System.out.println("Found: " + solution3.firstBadVersion(1));
        // Expected: 1
    }
}
