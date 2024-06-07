package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list/">206.ReverseLinkedList</a>
 * <img src="img.png" alt="Image description">
 */
public class ReverseLinkedListTest {
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
    }

    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode curr, ListNode prev) {
        // 현재 노드가 null이면 리턴
        if (curr == null) {
            return prev;
        }

        // 현재 노드의 다음 노드
        ListNode next = curr.next;

        // 현재 노드의 다음을 이전 노드로 지정
        curr.next = prev;

        // 다음 노드와 현재 노드를 파라미터로 하여 재귀 호출
        return reverse(next, curr);
    }

    @Test
    void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode reverse = reverseList(listNode1);
        while (reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
    }
}
