//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 268 👎 0


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        int ans = 0;
        int a = 0;
        int b = 0;
        int e = 0;
        int f  = 0;
        for(char c : s.toCharArray()){
            ans ^= c;
            a += c;
            e |= c;
        }
        for(char c : t.toCharArray()){
            ans ^= c;
            b += c;
            f |= c;
        }
        return ans == 0 && a == b && e == f;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
