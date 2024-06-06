package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/merge-two-sorted-lists/description/">21.MergeTwoSortedLists</a>
 * <img src="img.png" alt="Image description">
 */
public class MergeTwoSortedListsTest {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 두 노드 중 한 쪽이 널이면 널이 아닌 노드를 리턴
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        // list2가 더 크면 list1에 재귀 호출 결과를 엮고 list1을 리턴한다
        // 작은 쪽의 next를 새로 설정해줘야 하는데, 이 작은 node의 위치를 찾아줘야 한다
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    @Test
    void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode4;

        ListNode lstNode1 = new ListNode(1);
        ListNode lstNode3 = new ListNode(3);
        ListNode lstNode4 = new ListNode(4);
        lstNode1.next = lstNode3;
        lstNode3.next = lstNode4;

        ListNode head = mergeTwoLists(listNode1, lstNode1);
    }
}
