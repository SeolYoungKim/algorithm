package stackqueue;

public class MyStack2 {
    private static class Node {
        int item;
        Node next;

        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node last;

    public void push(int item) {
        this.last = new Node(item, this.last);
    }

    public int pop() {
        int item = this.last.item;
        this.last = this.last.next;
        return item;
    }
}
