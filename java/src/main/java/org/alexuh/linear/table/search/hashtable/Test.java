package org.alexuh.linear.table.search.hashtable;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 * hash表测试
 */
public class Test {

    public static void main(String[] args) {
        Emploee emo = new Emploee(2,"dsa2","10");
        Emploee emo1 = new Emploee(4,"dsa4","10");
        Emploee emo3 = new Emploee(1,"dsa1","10");
        HashTableDemo table = new HashTableDemo();
        table.add(emo);
        table.add(emo1);
        table.add(emo3);
        table.list();
        System.out.println("--------------");
        table.search(4);
    }

}
