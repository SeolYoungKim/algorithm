package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/description/">2.AddTwoNumbers</a>
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
        ListNode tempNode = new ListNode(0);  // 값 계산용 노드
        ListNode root = tempNode;  // 첫 번째 노드

        // 자릿수 합, 자리올림수, 나머지 저장 변수
        int sum = 0;
        int carry = 0;
        int remainder = 0;

        // 모든 연결리스트를 끝까지 순회 & 자리올림수도 0이 될 때까지 진행
        while (l1 != null || l2 != null || carry != 0) {
            sum = 0;
            // 첫 번째 연결 리스트 합산
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // 두 번째 연결 리스트 합산
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // 노드의 값으로 사용할 나머지 계산
            remainder = (sum + carry) % 10;

            // 자리올림수 계산
            carry = (sum + carry) / 10;

            // 계산 결과를 저장한다. tempNode는 임시 노드가 하나 있었기 때문에 next에 결과를 저장해줘야 한다
            tempNode.next = new ListNode(remainder);

            // 계산할 노드를 다음으로 이동
            tempNode = tempNode.next;
        }

        // 임시 노드 이후의 노드를 반환
        return root.next;
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
