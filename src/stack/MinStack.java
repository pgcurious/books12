package stack;

import java.util.Stack;

/**
 * LeetCode #155 - Min Stack
 * https://leetcode.com/problems/min-stack/
 *
 * Difficulty: Medium
 *
 * Problem: Design a stack that supports push, pop, top, and retrieving
 * the minimum element in constant time.
 *
 * All operations must be O(1):
 * - push(val): Push element onto stack
 * - pop(): Remove top element
 * - top(): Get top element
 * - getMin(): Retrieve minimum element
 */
public class MinStack {

    /**
     * INTUITION:
     * -----------
     * Regular stack gives O(1) for push/pop/top.
     * Challenge is O(1) getMin!
     *
     * Key insight: When we push, the minimum could change.
     * We need to track what the minimum WAS at each state of the stack.
     *
     * Approach 1: Two stacks
     * - Main stack for values
     * - Min stack tracks minimum at each level
     *
     * Approach 2: Single stack with pairs (value, minAtThisLevel)
     *
     * When we pop, the old minimum is restored automatically!
     */

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        // Push the new minimum onto minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());  // Keep current min
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("After pushing -2, 0, -3:");
        System.out.println("getMin(): " + minStack.getMin());  // Expected: -3

        minStack.pop();
        System.out.println("\nAfter pop:");
        System.out.println("top(): " + minStack.top());        // Expected: 0
        System.out.println("getMin(): " + minStack.getMin());  // Expected: -2

        System.out.println("\n--- Testing more operations ---");
        MinStack ms2 = new MinStack();
        ms2.push(2);
        ms2.push(1);
        ms2.push(3);
        System.out.println("Pushed 2, 1, 3");
        System.out.println("getMin(): " + ms2.getMin());  // Expected: 1
        ms2.pop();
        System.out.println("After pop, getMin(): " + ms2.getMin());  // Expected: 1
        ms2.pop();
        System.out.println("After another pop, getMin(): " + ms2.getMin());  // Expected: 2
    }
}

/**
 * Alternative: Space-optimized version
 * Only push to minStack when we have a new minimum
 */
class MinStackOptimized {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStackOptimized() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        // Only push if it's a new minimum (or equal)
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int popped = stack.pop();
        // Only pop from minStack if we're removing the current min
        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
