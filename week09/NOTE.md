## 学习笔记<br>
(感谢老师的点评！感谢老师在训练营期间给予的指导！老师辛苦啦！）<br>

### 本周感想：
本周学习的内容是字符串和高级动态规划，字符串部分整体感觉难度适中，可以接受。动态规划方面，在之前上过课后，经过五毒神掌的练习，感觉好了很多，部分一开始不懂的或者没理解的题，慢慢的也开始有了头绪、理解和掌握。回过头再来看这些题目，感觉正如超哥总结时候所说，dp的难点在于①状态的定义(数组的选择)，②状态方程的书写。<br><br>
比如在1143.最长公共子序列题目中(比较两个字符串text1和text2的最长公共子序列)，我们对于dp数组的定义是：
```
dp[text1.length() + 1][text2.length() + 1]
```
而不是:
```
dp[text1.length()][text2.length() ]
```
在这里专门空出了第一行和第一列，以便于后续的计算。这一步之前如果没有遇到过，自己很难可以想到。<br><br>
经过这一题之后，在做718.最长重复子数组（比较数组A和B的连续最长子数组）的题目时，我利用了类似的方法，建立了数组：
```
dp[A.length + 1][B.length + 1]
 ```
<br><br>
最后，感觉训练营时间过得好快，非常的舍不得！很幸运遇到了，优秀的超哥、优秀的助教老师以及优秀的大家！
<br>
<br>
### leetcode63 不同路径Ⅱ：
dp：
```
dp[i][j] = 0                            (obstacleGrid[i][j] = 0)
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]  (obstacleGrid[i][j] != 0)
```
代码：
```
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[j] = 0;
                if (j > 0 && obstacleGrid[i][j] != 1) dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
```
<br>
❤感谢老师的批阅❤
