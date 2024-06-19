package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list-ii/description/">92.ReverseLinkedListII</a>
 */
public class ReverseLinkedList2Test {
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
     * <img src="img_3.png" alt="Image description">
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        ListNode root = new ListNode(0);
        root.next = head;

        ListNode start = root;  // 변경이 시작되는 앞 지점. 여기를 기준으로 뒤에서 변경을 시작
        for (int i = 0; i < left - 1; i++) {
            start = start.next;
        }

        ListNode end = start.next;  // 변경이 시작되는 지점. 즉, 변경 구간 내에서 맨 뒤로 가야하는 지점
        // start와 temp 사이에 계속 새로운 노드를 끼워넣는 방식
        for (int i = 0; i < right - left; i++) {  // 지정된 개수만큼만 변경을 실행
            ListNode temp = start.next;  // start의 next를 임시로 저장
            start.next = end.next;  // end가 계속 우측으로 이동하면서 end의 next를 start 옆에 붙이는 원리
            end.next = end.next.next;
            start.next.next = temp;
        }

        return root.next;
    }

    // 1,4,3,2,5
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

        ListNode reverse = reverseBetween(listNode1, 2, 4);
        printNode(reverse);
    }

    private void printNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

}
