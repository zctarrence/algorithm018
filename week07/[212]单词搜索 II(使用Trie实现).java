//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 294 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TrieNode{
        TrieNode [] next;
        String isWord;
        TrieNode(){
            this.isWord = "";
            this.next = new TrieNode [26];
        }
    }
    class Trie{
        private TrieNode root;
        Trie(){
            this.root = new TrieNode();
        }
        public void insert(String x){
            TrieNode curr = root;
            for(int i=0;i<x.length();i++){
                TrieNode node = curr.next[x.charAt(i)-'a'];
                if(node==null){
                    node = new TrieNode();
                    curr.next[x.charAt(i)-'a'] = node;
                }
                curr = node;
                if(i==x.length()-1)     curr.isWord = x;
            }
        }
        public boolean contains(char x,TrieNode root){
            if(root.next[x-'a']!=null)  return true;
            else return false;
        }
    }
    List<String> res = new ArrayList<>();
    Trie tree = new Trie();
    int []x = new int []{-1,1,0,0};
    int [] y = new int []{0,0,-1,1};
    public List<String> findWords(char[][] board, String[] words) {
        for(String word:words){
            tree.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(tree.contains(board[i][j],tree.root)){
                    dfs(board,i,j,tree.root);
                }
            }
        }
        return res;
    }
    private void dfs(char[][] board,int i, int j, TrieNode root){
        TrieNode node = root.next[board[i][j]-'a'];
        if(!node.isWord.equals("")){
            res.add(node.isWord);
            node.isWord = "";
        }
        char ss = board[i][j];
        board[i][j] = '.';
        for(int index=0;index<4;index++){
            int newi = i+x[index];
            int newj = j+y[index];
            if(newi<0||newj<0||newi>=board.length||newj>=board[0].length||board[newi][newj]=='.')    continue;
            if(tree.contains(board[newi][newj],node))   dfs(board,newi,newj,node);
        }
        board[i][j] = ss;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
