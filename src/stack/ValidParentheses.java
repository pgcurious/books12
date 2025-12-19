package stack;

import java.util.*;

/**
 * LeetCode #20 - Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Difficulty: Easy
 *
 * Problem: Given a string containing just '(', ')', '{', '}', '[', ']',
 * determine if the input string is valid.
 *
 * Valid means:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of same type.
 *
 * Example:
 * Input: s = "()[]{}"
 * Output: true
 */
public class ValidParentheses {

    /**
     * INTUITION:
     * -----------
     * Stack is perfect for matching pairs!
     *
     * - When we see an opening bracket, push it onto stack
     * - When we see a closing bracket, check if it matches top of stack
     *   - If match, pop the opening bracket
     *   - If no match or stack empty, invalid
     * - At end, stack should be empty (all brackets matched)
     *
     * Think of it like nesting:
     * "({[]})" - innermost must close first before outer can close
     *
     * Time: O(n)
     * Space: O(n)
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Check closing brackets
            else {
                // No matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check if it matches
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // Stack should be empty (all brackets matched)
        return stack.isEmpty();
    }

    /**
     * Alternative: Push expected closing bracket instead
     * (slightly cleaner logic)
     */
    public boolean isValidAlt(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Push the EXPECTED closing bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            }
            // For closing brackets, check if it matches expected
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();

        System.out.println("'()': " + solution.isValid("()"));
        // Expected: true

        System.out.println("'()[]{}': " + solution.isValid("()[]{}"));
        // Expected: true

        System.out.println("'(]': " + solution.isValid("(]"));
        // Expected: false

        System.out.println("'([)]': " + solution.isValid("([)]"));
        // Expected: false (wrong nesting order)

        System.out.println("'{[]}': " + solution.isValid("{[]}"));
        // Expected: true
    }
}
