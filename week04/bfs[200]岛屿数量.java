//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 853 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;
        if (nr == 0 && nc == 0) return 0;
        for (int x = 0; x < nr; x++) {
            for (int y = 0; y < nc; y++) {
                if (grid[x][y] == '1') {
                    count ++;
                    bfs(grid, x, y);
                }
            }
        }
        return count;
    }
    public void bfs(char[][] grid, int x, int y) {
        int nr = grid.length;
        int nc = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            if (i >= 0 && j >= 0 && i < nr && j < nc && grid[x][y] == '1') {
                grid[i][j] = '0';
                bfs(grid, i - 1, j);
                bfs(grid, i + 1, y);
                bfs(grid, i, j - 1);
                bfs(grid, i, j + 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
