package com.iss.datastructure.linkedlist;

import java.util.Objects;

public class JosephuDemo3 {
    public static void main(String[] args) {
        SingleCircleLinkedList<Integer> list = new SingleCircleLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
//        list.list();
//        System.out.println(list.get(1));
//        list.delete(1);
//        System.out.println();
        calJosephu(list, 1, 2);
    }

    private static void calJosephu(SingleCircleLinkedList<Integer> list, int index, int m) {
        SingleCircleLinkedList<Integer> result = new SingleCircleLinkedList<>();
        SingleCircleLinkedList.Node<Integer> node = list.get(index);
        int count = 1;
        while (true) {
            if (count++ == m) {
                count = 1;
                result.add(node.item);
                list.delete(node.item);
            }
            if (node.next == node) {
                result.add(node.item);
                break;
            }
            node = node.next;
        }
        result.list();
    }

    static class SingleCircleLinkedList<E> {

        private Node<E> first;

        public Node<E> first() {
            return first;
        }

        public void add(E ele) {
            Node<E> newNode = new Node<>(ele);
            if (first == null) {
                first = newNode;
                first.next = first;
                return;
            }
            Node<E> temp = first;
            while (temp.next != first) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = first;
        }

        public Node<E> get(int index) {
            Node<E> temp = first;
            int tempIndex = 1;
            boolean isExist = false;
            while (true) {
                if (tempIndex++ == (index)) {
                    isExist = true;
                    break;
                }
                if (temp.next == first) {
                    break;
                }
                temp = temp.next;
            }
            if (!isExist) {
                return null;
            }
            return temp;
        }

        public void delete(E item) {
            Node<E> temp = first;
            boolean isExist = false;
            while (true) {
                if (Objects.equals(temp.next.item, item)) {
                    isExist = true;
                    break;
                }
                if (temp.next == first) {
                    break;
                }
                temp = temp.next;
            }
            if (temp.next == first) {
                first = temp.next.next;
            }
            if (isExist) {
                temp.next = temp.next.next;
            }
        }

        public void list() {
            Node<E> temp = first;
            while (true) {
                System.out.print(temp.item + "\t");
                if (temp.next == first) {
                    break;
                }
                temp = temp.next;
            }
            System.out.println();
        }

        static class Node<E> {
            private E item;
            private Node<E> next;

            public Node(E item) {
                this.item = item;
            }

            public Node(E item, Node<E> next) {
                this.item = item;
                this.next = next;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "item=" + item +
                        '}';
            }
        }
    }
}
