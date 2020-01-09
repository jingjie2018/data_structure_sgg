package com.iss.linkedlist;

import java.util.Objects;

public class JosephuDemo1 {
    public static void main(String[] args) {
        SingleCircleLinkedList<Integer> list = new SingleCircleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
//        list.list();
        System.out.println(list.get(1));
        list.delete(1);
//        System.out.println();
//        list.list();
        calJosephu(list, 2, 5);
    }

    private static void calJosephu(SingleCircleLinkedList<Integer> list, int index, int m) {
        SingleCircleLinkedList<Integer> result = new SingleCircleLinkedList<>();
        SingleCircleLinkedList.Node<Integer> head = list.first();
        SingleCircleLinkedList.Node<Integer> startNode = list.get(index);
        int count = 0;
        while (startNode.next != startNode) {
            count++;
            if (count == m) {
                count = 0;
                result.add(startNode.item);
                list.delete(startNode.item);
            }
            startNode = startNode.next;
        }
        result.add(startNode.item);
        result.list();
    }

    static class SingleCircleLinkedList<E> {

        private Node<E> first = new Node<>(null);

        public Node<E> first() {
            return first;
        }

        public void add(E ele) {
            Node<E> newNode = new Node<>(ele);
            Node<E> temp = first.next;
            if (temp == null) {
                first.next = newNode;
                newNode.next = newNode;
                return;
            }
            while (temp.next != first.next) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = first.next;
        }

        public Node<E> get(int index) {
            Node<E> temp = first.next;
            int tempIndex = 0;
            boolean isExist = false;
            while (temp.next != first.next) {
                if (tempIndex++ == (index - 1)) {
                    isExist = true;
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
            Node<E> temp = first.next;
            boolean isExist = false;
            while (temp.next != first.next) {
                if (Objects.equals(temp.next.item, item)) {
                    isExist = true;
                    break;
                }
                temp = temp.next;
            }
            if (isExist || Objects.equals(temp.next.item, item)) {
                first.next = temp.next.next;
                temp.next = temp.next.next;
            }
        }

        public void list() {
            Node<E> temp = first.next;
            while (temp.next != first.next) {
                System.out.print(temp.item + "\t");
                temp = temp.next;
            }
            System.out.println(temp.item);
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
