package org.alexuh.questions.leecode.struct;

import java.util.Arrays;

/**
 * Created by xuzhiceng@Gmail.com on 2022/12/12
 *
 * 并查集
 *
 */
public class BinChaji {

    public static void main(String[] args) {
        new BinChaji().testUionList();
    }

    public void testUionList() {
        // 初始化
        int n = 10;
        // end[i]: 第i个元素的祖先: 如 [0,2,3,3,5], 第二个元素(i=2)的祖先为end[2]=3, 3的祖先为end[3]=3,每个元素初始化祖先都等于自己,所以
        // i=2 第二个元素的祖先为3
        // 图正好可以使用012...表示几个顶点, 若是其他有权值的并查集数组长度需要初始化为maxValue,如 1，3，4，100，100的祖先是end[100]
        int[] end = new int[n];
        for(int i  = 0; i < n; i++) {
            end[i] = i;
        }
        System.out.println(Arrays.toString(end));
        union(end, 4, 5); // 4的祖先变成5
        union(end, 5, 7); // 5的祖先变成7，这样4的祖先也是7
        System.out.println(Arrays.toString(end));
        System.out.println(find(end, 4)); // 找4的祖先7
        System.out.println(find(end, 5)); // 找5的祖先7
    }

    // 查找某个元素的祖先
    public int find(int[] end, int j) {
        while (end[j] != j) {
            j = end[j];
        }
        return j;
    }

    // 合并集合(顶点)
    public void union(int[] end, int a, int b) {
        int i = find(end, a); // 获取元素a的祖先
        int j = find(end, b); // 获取元素b的祖先
        if (i != j) { // 如果祖先不同则需要将i的祖先指向j,这样i的祖先就是j, j也等于end[i]
            end[i] = j; // 将i的元素的祖先指向j
        }
    }


}
