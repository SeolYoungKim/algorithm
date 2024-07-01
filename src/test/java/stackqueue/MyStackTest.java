package stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/implement-stack-using-queues/submissions/1305796076/">225.Implement Stack using Queues</a>
 */
class MyStackTest {
    private final Queue<Integer> in;
    private final Queue<Integer> out;

    public MyStackTest() {
        this.in = new ArrayDeque<>();
        this.out = new ArrayDeque<>();
    }
    
    public void push(int x) {
        in.offer(x);
    }
    
    public int pop() {
        moveInToOut();
        Integer last = in.poll();
        moveOutToIn();

        return last;
    }

    private void moveInToOut() {
        int size = in.size();
        for (int i = 0; i < size - 1; i++) {
            out.offer(in.poll());
        }
    }

    private void moveOutToIn() {
        int size = out.size();
        for (int i = 0; i < size; i++) {
            in.offer(out.poll());
        }
    }

    public int top() {
        moveInToOut();
        Integer last = in.peek();
        out.offer(in.poll());
        moveOutToIn();

        return last;
    }
    
    public boolean empty() {
        return in.isEmpty();
    }

    @Test
    void test() {
        MyStackTest myStack = new MyStackTest();
        myStack.push(1);
        myStack.push(2);

        System.out.println(myStack.top());
        System.out.println(myStack.pop());

        System.out.println(myStack.empty());
    }
}