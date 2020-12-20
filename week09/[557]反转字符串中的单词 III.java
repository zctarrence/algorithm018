//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        char[] temp = s.toCharArray();
        int left = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == ' ') {
                reverse(temp, left, i - 1);
                left = i + 1;
            } else if (i == temp.length - 1) {
                reverse(temp, left, i);
            }
        }
        return new String(temp);

    }

    public void reverse(char[] temp, int left, int right) {
        while (left < right) {
            char c = temp[left];
            temp[left] = temp[right];
            temp[right] = c;
            left++;
            right--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
