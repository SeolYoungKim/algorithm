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
        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        in.push(x);
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
    }
    
    public int pop() {
        return in.pop();
    }
    
    public int peek() {
        return in.peek();
    }
    
    public boolean empty() {
        return in.isEmpty();
    }
}