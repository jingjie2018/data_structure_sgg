package com.iss.datastructure.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList.HeroNode2 heroNode1 = new DoubleLinkedList.HeroNode2(1, "宋江", "及时雨");
        DoubleLinkedList.HeroNode2 heroNode2 = new DoubleLinkedList.HeroNode2(2, "卢俊义", "玉麒麟");
        DoubleLinkedList.HeroNode2 heroNode3 = new DoubleLinkedList.HeroNode2(3, "吴用", "智多星");
        DoubleLinkedList.HeroNode2 heroNode4 = new DoubleLinkedList.HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList singleLinkedList = new DoubleLinkedList();
        System.out.println("add");
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode4);
        singleLinkedList.add(heroNode2);
        singleLinkedList.list();

        System.out.println("addByOrder");
//        singleLinkedList.addByOrder(heroNode1);
//        singleLinkedList.addByOrder(heroNode4);
//        singleLinkedList.addByOrder(heroNode4);
//        singleLinkedList.addByOrder(heroNode3);
//        singleLinkedList.addByOrder(heroNode3);
//        singleLinkedList.addByOrder(heroNode3);
//        singleLinkedList.addByOrder(heroNode2);
//        singleLinkedList.list();

        System.out.println("update");
        heroNode2.setName(heroNode2.getNickName());
        singleLinkedList.update(heroNode2);
        singleLinkedList.list();

        System.out.println("delete");
//        singleLinkedList.delete(heroNode2);
//        singleLinkedList.delete(6);
        singleLinkedList.list();

        System.out.println("size");
        System.out.println(singleLinkedList.size());

        System.out.println("get(Index)");
        System.out.println(singleLinkedList.get(2));

        System.out.println("reverse");
        singleLinkedList.reverse();
        singleLinkedList.list();

        System.out.println("reversePrint");
        singleLinkedList.reversePrint();
        singleLinkedList.list();

        System.out.println("sort");
        singleLinkedList.sort();
        singleLinkedList.list();
        System.out.println("head rear");
        System.out.println(singleLinkedList.head());
        System.out.println(singleLinkedList.rear());

        DoubleLinkedList.HeroNode2 heroNode5 = new DoubleLinkedList.HeroNode2(5, "宋江", "及时雨");
        DoubleLinkedList.HeroNode2 heroNode6 = new DoubleLinkedList.HeroNode2(6, "卢俊义", "玉麒麟");
        DoubleLinkedList.HeroNode2 heroNode7 = new DoubleLinkedList.HeroNode2(7, "吴用", "智多星");
        DoubleLinkedList.HeroNode2 heroNode8 = new DoubleLinkedList.HeroNode2(8, "林冲", "豹子头");

        DoubleLinkedList singleLinkedList2 = new DoubleLinkedList();
        System.out.println("singleLinkedList2--add");
        singleLinkedList2.add(heroNode8);
        singleLinkedList2.add(heroNode5);
        singleLinkedList2.add(heroNode7);
        singleLinkedList2.add(heroNode6);
        singleLinkedList2.list();
        System.out.println("singleLinkedList--addAll");
        singleLinkedList.addAll(singleLinkedList2);
        singleLinkedList.list();
    }
}

// 定义链表
class DoubleLinkedList {
    // 初始化一个头结点
    private HeroNode2 head = new HeroNode2();

    // 添加结点
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    // 排名
    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (heroNode2.no < temp.next.no) {
                break;
            }
            if (heroNode2.no == temp.next.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("已存在编号为%d的英雄，不添加。\n", heroNode2.no);
            return;
        }
        heroNode2.next = temp.next;
        temp.next = heroNode2;
        heroNode2.pre = temp;
        if (heroNode2.next != null) {
            heroNode2.next.pre = heroNode2;
        }
    }

    // 修改结点
    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            if (heroNode2.no == temp.no) {
                break;
            }
            temp = temp.next;
        }
        if (temp == head) {
            System.out.printf("没有找到编号为%d的英雄。\n", heroNode2.no);
            return;
        }
        temp.name = heroNode2.name;
        temp.nickName = heroNode2.nickName;
    }

    // 刪除结点
    public void delete(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            if (heroNode2.no == temp.no) {
                break;
            }
            temp = temp.next;
        }
        if (temp.no != heroNode2.no) {
            System.out.printf("没有找到编号为%d的英雄。\n", heroNode2.no);
            return;
        }
        temp.pre.next = temp.next;
        if (temp.next != null) {
            temp.next.pre = temp.pre;
        }
    }

    // 刪除结点
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            if (no == temp.no) {
                break;
            }
            temp = temp.next;
        }
        if (temp.no != no) {
            System.out.printf("没有找到编号为%d的英雄。\n", no);
            return;
        }
        temp.pre.next = temp.next;
        if (temp.next != null) {
            temp.next.pre = temp.pre;
        }
    }

    // 遍历
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    public int size() {
        if (head.next == null) {
            return 0;
        }
        int size = 0;
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    public HeroNode2 get(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int tempIndex = 0;
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (index == tempIndex) {
                break;
            }
            tempIndex++;
        }
        return temp;
    }

    // 反转
    public void reverse() {
        // 空链表或者只有一个元素的链表不需要反转
        if (head.next == null || head.next.next == null) {
            System.out.println("不需要反转。");
            return;
        }
        HeroNode2 reverseNode = new HeroNode2();
        HeroNode2 current = head.next; // 当前节点
        HeroNode2 next = null; // 临时保存当前节点的下一个节点
        while (current != null) {
            next = current.next; // 临时保存当前节点的下一个结点
            current.next = reverseNode.next; // 将当前节点放置于reverseNode之后
            reverseNode.next = current;
            // 重置current，设置为当前节点的下一个结点
            current = next;
        }
        head.next = reverseNode.next;
    }

    // 反转打印
    public void reversePrint() {
        HeroNode2[] nodes = new HeroNode2[size()];
        HeroNode2 temp = head;
        int index = 0;
        while (temp.next != null) {
            nodes[index++] = temp.next;
            temp = temp.next;
        }
        for (int i = nodes.length - 1; i >= 0; i--) {
            HeroNode2 node = nodes[i];
            System.out.println(node);
        }
    }

    public HeroNode2 head() {
        return head;
    }

    public HeroNode2 rear() {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    // 合并有序的两个单链表
    public void addAll(DoubleLinkedList list2) {
        HeroNode2 rear = this.rear();
        HeroNode2 head = list2.head();
        rear.next = head.next;
        head.next = null;
        this.sort();
    }

    // 合并有序的两个单链表
    public void sort() {
        // 空链表或者只有一个元素的链表不需要排序
        if (head.next == null || head.next.next == null) {
            System.out.println("不需要排序。");
            return;
        }
        HeroNode2 sortNode = new HeroNode2();
        HeroNode2 current = head.next; // 当前节点
        HeroNode2 next = null; // 临时保存当前节点的下一个节点
        while (current != null) {
            next = current.next; // 临时保存当前节点的下一个结点
            HeroNode2 temp = sortNode;
            while (temp.next != null) {
                if (temp.next.no > current.no) {
                    break;
                }
                temp = temp.next;
            }
            current.next = temp.next;
            temp.next = current;
            // 重置current，设置为当前节点的下一个结点
            current = next;
        }
        head.next = sortNode.next;
    }

    // 定义Node
    public static class HeroNode2 {
        private int no;
        private String name;
        private String nickName;
        private HeroNode2 next;
        private HeroNode2 pre;

        public HeroNode2() {
        }

        public HeroNode2(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public HeroNode2 getNext() {
            return next;
        }

        public void setNext(HeroNode2 next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
