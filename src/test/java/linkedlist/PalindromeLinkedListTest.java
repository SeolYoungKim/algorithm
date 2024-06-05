package linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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
        List<Integer> list = new ArrayList<>();

        ListNode node = head;
        while (node.next != null) {
            list.add(node.val);
            node = node.next;
        }
        list.add(node.val);

        int leftIdx = 0;
        int rightIdx = list.size() - 1;

        while (leftIdx < rightIdx) {
            if (!list.get(leftIdx).equals(list.get(rightIdx))) {
                return false;
            }

            leftIdx++;
            rightIdx--;
        }

        return true;
    }

    @Test
    void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        assertThat(isPalindrome(node1)).isTrue();
    }
}
