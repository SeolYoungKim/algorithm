package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/odd-even-linked-list/description/">328.OddEvenLinkedList</a>
 */
public class OddEvenLinkedListTest {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        ListNode evenRoot = new ListNode(0);
        ListNode even = evenRoot;

        while (node != null && node.next != null) {
            ListNode next = node.next;
            even.next = next;
            even = next;

            node.next = next.next;
            node = next.next;
        }

        if (node != null) {
            even.next = null;
        }

        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = evenRoot.next;
        return head;
    }

    @Test
    void testTrue() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // expected = [1,3,5,2,4]
        ListNode listNode = oddEvenList(node1);
//        printNode(listNode);
    }

    private void printNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    @Test
    void testTrue2() {
        ListNode listNode = new ListNode(1);
    }
}
