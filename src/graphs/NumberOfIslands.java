package graphs;

/**
 * LeetCode #200 - Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * Difficulty: Medium
 *
 * Problem: Given a 2D grid of '1's (land) and '0's (water),
 * count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically.
 *
 * Example:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {

    /**
     * INTUITION:
     * -----------
     * Classic graph traversal problem! Each island is a connected component.
     *
     * Strategy:
     * 1. Scan the grid cell by cell
     * 2. When we find a '1' (land), we found a new island
     * 3. Use DFS to "sink" the entire island (mark all connected '1's as visited)
     * 4. Count how many times we start a new DFS
     *
     * Why "sink" the island?
     * - Once visited, we mark cells as '0' to avoid counting again
     * - This is a common pattern: modify input to track visited cells
     *
     * Time: O(m * n) - visit each cell once
     * Space: O(m * n) - worst case recursion depth
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    numIslands++;      // Found new island
                    dfs(grid, r, c);   // Sink the entire island
                }
            }
        }

        return numIslands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Boundary check and water check
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return;
        }

        // Sink this land (mark as visited)
        grid[r][c] = '0';

        // Explore all 4 directions
        dfs(grid, r - 1, c);  // Up
        dfs(grid, r + 1, c);  // Down
        dfs(grid, r, c - 1);  // Left
        dfs(grid, r, c + 1);  // Right
    }

    /**
     * Alternative: BFS approach
     */
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    numIslands++;
                    bfs(grid, r, c);
                }
            }
        }

        return numIslands;
    }

    private void bfs(char[][] grid, int startR, int startC) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.offer(new int[]{startR, startC});
        grid[startR][startC] = '0';  // Mark visited immediately

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols
                    && grid[newR][newC] == '1') {
                    queue.offer(new int[]{newR, newC});
                    grid[newR][newC] = '0';  // Mark visited when adding to queue
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();

        // Test case 1
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println("Grid 1:");
        printGrid(grid1);
        // Make a copy since we modify the grid
        char[][] grid1Copy = copyGrid(grid1);
        System.out.println("Number of islands: " + solution.numIslands(grid1Copy));
        // Expected: 1

        // Test case 2
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("\nGrid 2:");
        printGrid(grid2);
        char[][] grid2Copy = copyGrid(grid2);
        System.out.println("Number of islands: " + solution.numIslands(grid2Copy));
        // Expected: 3
    }

    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    private static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
}
