package org.alexuh.alg;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/8
 *
 * 汉诺塔游戏
 * a  b c
 *
 */
public class Hanuota {

    public static void main(String[] args) {
        hannuota(4, "A", "B", "C");
    }

    public static void hannuota(int num, String a, String b , String c) {
        if (num == 1) {
            System.out.printf("第一个盘从%s -> %s%n", a, c);
        }else {
            hannuota(num - 1, a, c, b);
            System.out.printf("第%s个盘从%s -> %s", num,a,c);
            System.out.println();
            hannuota(num - 1, b, a, c);
        }
    }
}
