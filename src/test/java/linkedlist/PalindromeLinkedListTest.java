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
        if (head.next == null) {
            return true;
        }

        ListNode reverseHead = reverse(head);

        while (head.next != null && reverseHead.next != null) {
            if (head.val != reverseHead.val) {
                return false;
            }
            head = head.next;
            reverseHead = reverseHead.next;
        }

        return head.val == reverseHead.val;
    }

    public ListNode reverse(ListNode head) {
        ListNode reverseNode = new ListNode(head.val);

        ListNode node = head.next;
        while (node.next != null) {
            ListNode newNode = new ListNode(node.val);
            newNode.next = reverseNode;
            reverseNode = newNode;
            node = node.next;
        }
        ListNode lastNode = new ListNode(node.val);
        lastNode.next = reverseNode;
        reverseNode = lastNode;

        return reverseNode;
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
