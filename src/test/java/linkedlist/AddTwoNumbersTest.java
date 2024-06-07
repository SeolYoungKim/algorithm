package linkedlist;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/description/">2.AddTwoNumbers</a>
 * <img src="img.png" alt="Image description">
 */
public class AddTwoNumbersTest {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger resultNumber = convertToNumber(l1).add(convertToNumber(l2));
        String resultStr = String.valueOf(resultNumber);  // 807

        ListNode head = null;
        ListNode node = null;
        for (int i = resultStr.length() - 1; i >= 0 ; i--) {
            int num = Character.getNumericValue(resultStr.charAt(i));
            ListNode newNode = new ListNode(num);
            if (node != null) {
                node.next = newNode;
            } else {
                head = newNode;
            }

            node = newNode;
        }

        return head;
    }

    public BigInteger convertToNumber(ListNode l1) {
        StringBuilder sb1 = new StringBuilder();
        while (l1 != null) {
            sb1.append(l1.val);
            l1 = l1.next;
        }

        return new BigInteger(sb1.reverse().toString());
    }

    @Test
    void test() {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode4 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode4;

        ListNode lstNode1 = new ListNode(5);
        ListNode lstNode3 = new ListNode(6);
        ListNode lstNode4 = new ListNode(4);
        lstNode1.next = lstNode3;
        lstNode3.next = lstNode4;

        ListNode head = addTwoNumbers(listNode1, lstNode1);
        printResult(head);
    }

    @Test
    void test2() {
        ListNode listNode = new ListNode(1);

        // 노드 생성
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);

        // 노드 연결
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;

        ListNode result = addTwoNumbers(listNode, listNode1);
        printResult(result);
    }

    private void printResult(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
