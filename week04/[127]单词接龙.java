//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 639 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }
            Set<String> visited = new HashSet<>();
            Set<String> beginVisited = new HashSet<>();
            beginVisited.add(beginWord);
            Set<String> endVisited = new HashSet<>();
            endVisited.add(endWord);
            int step = 1;
            while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
                if (beginVisited.size() > endVisited.size()) {
                    Set<String> temp = beginVisited;
                    beginVisited = endVisited;
                    endVisited = temp;
                }
                Set<String> nextLevelVisited = new HashSet<>();
                for (String word : beginVisited) {
                    if (changeWordEveryOneLetter(word, endVisited, visited, wordSet, nextLevelVisited)) {
                        return step + 1;
                    }
                }
                beginVisited = nextLevelVisited;
                step++;
            }
            return 0;
        }

        private boolean changeWordEveryOneLetter(String word, Set<String> endVisited,
                                                 Set<String> visited,
                                                 Set<String> wordSet,
                                                 Set<String> nextLevelVisited) {
            char[] charArray = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char originChar = charArray[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (originChar == c) {
                        continue;
                    }
                    charArray[i] = c;
                    String nextWord = String.valueOf(charArray);
                    if (wordSet.contains(nextWord)) {
                        if (endVisited.contains(nextWord)) {
                            return true;
                        }
                        if (!visited.contains(nextWord)) {
                            nextLevelVisited.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                charArray[i] = originChar;
            }
            return false;
        }
}
//leetcode submit region end(Prohibit modification and deletion)
