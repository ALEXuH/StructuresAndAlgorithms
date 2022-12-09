package org.alexuh.linear.table.linked;

/**
 * Created by xuzhiceng@Gmail.com on 2022/10/21
 */
public class CircleNode {
    public int no;
    public CircleNode next;

    public CircleNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "CircleNode{" +
                "no=" + no +
                '}';
    }
}
