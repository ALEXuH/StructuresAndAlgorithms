package org.alexuh.questions.leecode.struct;

/**
 * Created by xuzhiceng@Gmail.com on 2022/12/12
 *
 * 字典树/前缀树/单词查找树
 *
 *用于匹配单词(输入框智能匹配)，前缀单词查询
 *
 * 数据结构: 类似于多叉树
 * [go, goc, gob,goo]  [he,hell,hela]
 *
 *            root
 *        g           h
 *      o           e
 *   o  b  c       l
 *               l  a
 *
 */
public class Trie {
    public boolean isWorld; // 是否结束前面是一个单词
    public Trie[] children = new Trie[26]; // 代表26个字母 ,'b' - 'a' = index,表示字母的下标,所以只需要判断该index位置是否为空就可以得到当前字母的匹配

    public Trie(){}

    public void insert(String word) {
        // 遍历单词字符,如果遇到节点为空则添加该字母的节点（也就是在对应字母的所以处添加node），如果非空继续向下寻找。
        Trie curNode = this; // 表示根节点
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curNode.children[index] == null) {
                curNode.children[index] = new Trie();
            }
            curNode = curNode.children[index]; // 从下一个字符继续寻找添加下一个字符
        }
        curNode.isWorld = true; // 表示前面为一个单词

    }
    // 查找单词是否存在
    public boolean search(String word) {
        // 查找是否存在,从第一个字符开始遍历，依次往下寻找，如果查找到其中有为空的说明不存在,如果查到都有并且最后的isworld=true说明是存在的单词
        Trie curNode = this; // 表示根节点
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curNode.children[index] == null) {
                return false;
            }
            curNode = curNode.children[index];
        }
        // 查找到字符都存在，然后判断最后一个字符是否是中止的地方
        return curNode.isWorld;

    }
    // 查找前缀是否存在
    public boolean startsWith(String prefix) {
        // 查找是否存在,从第一个字符开始遍历，依次往下寻找，如果查找到其中有为空的说明不存在
        Trie curNode = this; // 表示根节点
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (curNode.children[index] == null) {
                return false;
            }
            curNode = curNode.children[index];
        }
        return true;
    }
}
