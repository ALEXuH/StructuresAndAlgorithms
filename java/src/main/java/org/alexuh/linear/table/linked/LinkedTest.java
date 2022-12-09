package org.alexuh.linear.table.linked;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/20
 */
public class LinkedTest {
    public static void main(String[] args) {
        HeroNode a1 = new HeroNode(1, "宋江", "智多星");
        HeroNode a2 = new HeroNode(2, "宋江1", "智多星1");
        HeroNode a3 = new HeroNode(3, "宋江2", "智多星2");

        ArrayLinked link = new ArrayLinked();
        // 添加数据
        link.add(a1);
        link.add(a2);
        link.add(a3);
        link.list();
        // 查询更新删除节点
        System.out.println("-----");
        link.search(3);
        System.out.println("-----");
        link.update(3, "aa", "aaa");
        link.list();
        link.del(3);
        link.list();
        System.out.println("---------");
        link.clear();
        link.list();

        HeroNode a4 = new HeroNode(4, "宋江", "智多星");
        HeroNode a5 = new HeroNode(5, "宋江1", "智多星1");
        HeroNode a6 = new HeroNode(6, "宋江2", "智多星2");
        link.addOrder(a6);
        link.addOrder(a5);
        link.addOrder(a4);
        // 添加相同编号
        //link.addOrder(a4);
        //link.addOrder(a4);
        link.list();

        System.out.println("----------");
        // 测试：获取倒数第1和2个节点
        System.out.println("倒数第一个节点" + link.getKFromEnd(a4, 1).toString());
        System.out.println("倒数第二个节点" + link.getKFromEnd(a4, 2).toString());
        System.out.println("倒数第三个节点" + link.getKFromEnd(a4, 3).toString());
        System.out.println("----");
        link.list();
        System.out.println("----");
        link.reserveLinked();
        link.list();
        System.out.println("------");
        link.list();
        System.out.println("--------");
        link.reserverList();

    }

}
