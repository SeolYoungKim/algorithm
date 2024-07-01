package stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/implement-stack-using-queues/submissions/1305796076/">225.Implement Stack using Queues</a>
 */
class MyStackTest {
    private final Queue<Integer> queue;

    public MyStackTest() {
        this.queue = new ArrayDeque<>();
    }
    
    public void push(int x) {
        // 큐의 가장 마지막에 위치함
        queue.offer(x);

        // 나머지를 방금 들어온 요소의 뒤로 보냄
        for (int i = 1; i < queue.size(); i++) {
            queue.offer(queue.poll());
        }
    }
    
    public int pop() {
        return queue.poll();
    }

    int top() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
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