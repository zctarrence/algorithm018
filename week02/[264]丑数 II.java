//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 417 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int[] uglyNumber = {2,3,5};
    public int nthUglyNumber(int n) {

            PriorityQueue<Long> queue = new PriorityQueue<>();
            queue.add(1l);
            int count = 0;
            while (! queue.isEmpty()){
                long cut = queue.poll();

                if(++count >= n){
                    return (int) cut;
                }
                for(int num : uglyNumber){
                    if(! queue.contains(num * cut)){
                        queue.add(num * cut);
                    }
                }
            }
            return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
