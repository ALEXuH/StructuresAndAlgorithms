package org.alexuh.linear.table.linked;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/20
 */
public class HeroNode {
    public int num;  // 英雄编号
    public String name; // 英雄姓名
    public String nickname; // 英雄昵称
    public HeroNode next;     // 下一个节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public HeroNode(int num, String name, String nickname) {
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }



}
