package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    private final Deque<Integer> in;
    private final Deque<Integer> out;

    public MyQueue() {
        this.in = new ArrayDeque<>();
        this.out = new ArrayDeque<>();
    }
    
    public void push(int x) {
        in.push(x);
    }
    
    public int pop() {
        peek();
        return out.pop();
    }
    
    public int peek() {
        // in 스택을 뒤집는 작업 (결과물이 out)
        // out이 비었을 때만 진행 (한번도 peek() or pop() 을 호출하지 않은 경우)
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }

        return out.peek();
    }
    
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}