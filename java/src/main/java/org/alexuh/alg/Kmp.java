package org.alexuh.alg;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/8
 */
public class Kmp {

    public static void main(String[] args) {
        String t = "ABCDABDKA";
        String p = "ABD";
        String p1 = "ABDD";
        System.out.println(bf(t, p));
        System.out.println(bf(t, p1));

        System.out.println(kmp(t, p));
        System.out.println(kmp(t, p1));

        String t1 = "aabaabcdaabaaab";
        String t2 = "aabaadsdaaabaaf";
        System.out.println(kmp(t2, t1));

        
        System.out.println(Arrays.toString(getNext("aabcaabadd")));
    }

//    // kmp匹配
    public static int kmp(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        if (textLen  < patternLen) {
            return -1;
        }
        int[] next = getNext(pattern);
        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();
        // 遍历字符串并不断调整j匹配串的位置 i字符串索引 j匹配串索引
        for(int i = 0, j = 0; i < textLen; i++){
            // 不成功根据next调整模式串位置
            while(j > 0 && textChar[i] != patternChar[j]) {
                // j-1 : textChar[i] != patternChar[j]所以要看前面j-1元素是否有部分匹配的
                // j: next[j]表示前后缀公共部分最长的公共字符长度, j=len(0-重复的地方),所以继续比较下一位patterChar[len]也就是patternChar[j]
                j = next[j - 1];
            }
            //  匹配成功继续下一个匹配
            if (textChar[i] == patternChar[j]) {
                j++;
            }
            if (j == patternLen) {
                return i - j + 1;
            }

        }
        return -1;
    }


    // 暴力匹配
    public static int bf(String text, String pattern) {

        int textLen = text.length();
        int patternLen = pattern.length();
        if (textLen  < patternLen) {
            return -1;
        }

        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();

        int i = 0; // 文本索引
        int j = 0; // 模式串索引

        //  当匹配到第一个或者匹配完却没有时退出
        while(i < textLen && j < patternLen) {
            // 如果相等继续匹配下一个字符
            if (textChar[i] == patternChar[j]) {
                i++;
                j++;
            } else {
                // 匹配失败让j重置为0,i回到第一个匹配字符的下一个位置
                i = (i - j) + 1;
                j = 0;
            }
        }

        // 判断是否匹配到,j都匹配完还加两1所以当j==len时匹配到
        if (j == patternLen) {
            return i - j;
        } else {
            return -1;
        }
    }

    // KMP匹配 ABCDAB (6-2)  ABCABC (6 - 3) 移动位数 = 字符串长度 - 部份匹配值
    // 使用while 从前往后推依次递加位数
    public static int[] getNext(String str) {
        char[] s = str.toCharArray();
        int[] next = new int[s.length];
        int i = 0;   // i前缀末尾
        //int j = 1; // 后缀末尾
        next[0] = 0;
        // j后缀末尾
        for(int j = 1; j < s.length; j++) {
            while (i > 0 && s[j] != s[i]) {
                i = next[i - 1];
            }
            if (s[j] == s[i]) {
                i++;
            }
            next[j] = i;
        }
        return next;
    }


}
