package dequepriorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {
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

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing((ListNode node) -> node.val));
        for (ListNode node : lists) {
            while (node != null) {
                pq.offer(node);
                ListNode next = node.next;
                node.next = null;
                node = next;
            }
        }

        ListNode head = pq.poll();
        ListNode prev = head;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            prev.next = node;
            prev = node;
        }

        return head;
    }
}
