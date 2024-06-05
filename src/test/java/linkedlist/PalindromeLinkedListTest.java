package linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
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
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode node = head;
        while (node != null) {
            deque.add(node.val);
            node = node.next;
        }

        while (!deque.isEmpty() && deque.size() > 1) {
            Integer left = deque.removeFirst();
            Integer right = deque.removeLast();
            if (!left.equals(right)) {
                return false;
            }
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
