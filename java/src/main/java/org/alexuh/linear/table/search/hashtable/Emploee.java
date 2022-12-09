package org.alexuh.linear.table.search.hashtable;

/**
 * Created by xuzhiceng@Gmail.com on 2022/11/2
 *链表节点
 */
public class Emploee {
    public int id;
    public String name;
    public String age;
    public Emploee next;

    public Emploee(int id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Emploee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
