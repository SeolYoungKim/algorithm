package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/">24.SwapNodesInPairs</a>
 */
public class SwapNodesInPairsTest {
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

    /**
     * <img src="img_1.png" alt="Image description">
     */
    public ListNode swapPairs(ListNode head) {
        // 값을 계산할 임시 노드
        ListNode node = new ListNode(0);

        // 임시 노드를 첫 번째 노드로 선언
        ListNode root = node;

        // 다음 노드를 head로 지정
        node.next = head;

        // 다음 노드와 다음.다음 노드가 있으면 반복
        while (node.next != null && node.next.next != null) {
            // node -> a -> b -> c
            ListNode a = node.next;
            ListNode b = node.next.next;

            // swap
            // swap 결과 : node -> b -> a -> c
            a.next = b.next;     // a -> c
            node.next = b;       // node -> b
            node.next.next = a;  // b -> a

            // 두 칸 앞으로 이동
            node = node.next.next;  // c -> ...
        }

        return root.next;
    }


    @Test
    void testTrue() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode listNode = swapPairs(node1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    @Test
    void testTrue2() {
        ListNode listNode = new ListNode(1);
    }
}
