//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学 
// 👍 264 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPowerOfTwo(int n) {
        //解释：因为(n & (n - 1)可以去除 n 的位级表示中最低的那一位 1，而一个数若为2的幂，则该数的二进制形式只能有一个1。
        //     因此将n的最低位的1变为0后，若n为0，说明是2的幂，否则不是。
        return n > 0 && (n & (n - 1))==0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
