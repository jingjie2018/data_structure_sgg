package com.iss.linkedlist;

import java.util.Objects;

public class JosephuDemo2 {
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
        SingleCircleLinkedList.Node<Integer> head = list.head();
        SingleCircleLinkedList.Node<Integer> startNode = list.get(index);
        int count = 0;
        while (startNode.next != startNode) {
            if (head.equals(startNode)) {
                startNode = startNode.next;
                continue;
            }
            count++;
            if (count == m) {
                count = 0;
                result.add(startNode.item);
                list.delete(startNode.item);
            }
            startNode = startNode.next;
        }
        result.list();
    }

    static class SingleCircleLinkedList<E> {

        private Node<E> head;

        {
            head = new Node<>(null);
            head.next = head;
        }

        public Node<E> head() {
            return head;
        }

        public void add(E ele) {
            Node<E> newNode = new Node<>(ele);
            Node<E> temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }

        public Node<E> get(int index) {
            Node<E> temp = head;
            int tempIndex = 0;
            boolean isExist = false;
            while (temp.next != head) {
                if (tempIndex++ == (index - 1)) {
                    isExist = true;
                    break;
                }
                temp = temp.next;
            }
            if (!isExist) {
                return null;
            }
            return temp.next;
        }

        public E delete(E item) {
            Node<E> temp = head;
            boolean isExist = false;
            while (temp.next != head) {
                if (Objects.equals(temp.next.item, item)) {
                    isExist = true;
                    break;
                }
                temp = temp.next;
            }
            if (!isExist) {
                return null;
            }
            E result = temp.next.item;
            temp.next = temp.next.next;
            return result;
        }

        public void list() {
            Node<E> temp = head;
            while (temp.next != head) {
                temp = temp.next;
                System.out.print(temp.item + "\t");
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

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node<?> node = (Node<?>) o;
                return Objects.equals(item, node.item) &&
                        Objects.equals(next, node.next);
            }

            @Override
            public int hashCode() {
                return Objects.hash(item, next);
            }
        }
    }
}
