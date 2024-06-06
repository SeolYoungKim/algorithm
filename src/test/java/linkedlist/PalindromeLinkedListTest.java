package linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/palindrome-linked-list/description/">234.PalindromeLinkedList</a>
 */
public class PalindromeLinkedListTest {
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

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 러너 이동
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 입력값이 홀수일 때는 fast가 한칸을 덜 이동하여 null이 아닐 것이다
        if (fast != null) {
            // 입력값이 홀수일 때는 slow를 한칸 이동시켜야 한다 (중앙을 빗겨나가기 위함)
            slow = slow.next;
        }

        // 중간 지점에 도달한 slow 러너를 기준으로 역순으로 연결 리스트를 만들어 나간다
        ListNode reverse = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = next;
        }

        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }

        return true;
    }

    @Test
    void testTrue() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        assertThat(isPalindrome(node1)).isTrue();
    }

    @Test
    void testTrue2() {
        ListNode listNode = new ListNode(1);
        assertThat(isPalindrome(listNode)).isTrue();
    }

    @Test
    void testFalse() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        assertThat(isPalindrome(node1)).isFalse();
    }
}
