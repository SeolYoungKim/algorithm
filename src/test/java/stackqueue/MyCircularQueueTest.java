package stackqueue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/design-circular-queue/submissions/1305851624/">622</a>
 */
class MyCircularQueueTest {
    static class MyCircularQueue {
        private final int[] array;
        private int frontIdx;
        private int insertIdx;
        private int size;

        public MyCircularQueue(int k) {
            this.array = new int[k];
            Arrays.fill(array, -1);
        }

        // 삽입
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            array[insertIdx] = value;
            size++;
            insertIdx = (insertIdx + 1) % array.length;
            return true;
        }

        // 삭제
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            array[frontIdx] = -1;
            frontIdx = (frontIdx + 1) % array.length;
            size--;
            return true;
        }

        // 맨앞
        public int Front() {
            return array[frontIdx];
        }

        // 맨뒤
        public int Rear() {
            if (insertIdx == 0) {
                return array[array.length - 1];
            }

            int rearIdx = insertIdx - 1;
            return array[rearIdx];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }
    }

    @Test
    void enQueueTest() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.enQueue(1);

        assertThat(myCircularQueue.frontIdx).isEqualTo(0);
        assertThat(myCircularQueue.size).isEqualTo(1);
        assertThat(myCircularQueue.insertIdx).isEqualTo(1);
        assertThat(myCircularQueue.array).contains(1);

        myCircularQueue.enQueue(2);
        assertThat(myCircularQueue.frontIdx).isEqualTo(0);
        assertThat(myCircularQueue.size).isEqualTo(2);
        assertThat(myCircularQueue.insertIdx).isEqualTo(2);
        assertThat(myCircularQueue.array).contains(1, 2);

        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(4);
        myCircularQueue.enQueue(5);
        boolean isSuccess = myCircularQueue.enQueue(6);

        assertThat(myCircularQueue.frontIdx).isEqualTo(0);
        assertThat(myCircularQueue.size).isEqualTo(5);
        assertThat(myCircularQueue.insertIdx).isEqualTo(0);
        assertThat(myCircularQueue.array).contains(1, 2, 3, 4, 5);
        assertThat(isSuccess).isFalse();
    }

    @Test
    void deQueueTest() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(4);
        myCircularQueue.enQueue(5);

        myCircularQueue.deQueue();
        assertThat(myCircularQueue.frontIdx).isEqualTo(1);
        assertThat(myCircularQueue.size).isEqualTo(4);
        assertThat(myCircularQueue.insertIdx).isEqualTo(0);
        assertThat(myCircularQueue.array).contains(2, 3, 4, 5);

        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        assertThat(myCircularQueue.frontIdx).isEqualTo(4);
        assertThat(myCircularQueue.size).isEqualTo(1);
        assertThat(myCircularQueue.insertIdx).isEqualTo(0);
        assertThat(myCircularQueue.array).contains(5);

        myCircularQueue.deQueue();
        boolean isSuccess = myCircularQueue.deQueue();
        assertThat(myCircularQueue.frontIdx).isEqualTo(0);
        assertThat(myCircularQueue.size).isEqualTo(0);
        assertThat(myCircularQueue.insertIdx).isEqualTo(0);
        assertThat(myCircularQueue.array).containsOnly(-1);
        assertThat(isSuccess).isFalse();
    }

    @Test
    void frontRearTest() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(4);
        myCircularQueue.enQueue(5);

        assertThat(myCircularQueue.Front()).isEqualTo(1);
        assertThat(myCircularQueue.Rear()).isEqualTo(5);

        myCircularQueue.deQueue();
        assertThat(myCircularQueue.Front()).isEqualTo(2);
        assertThat(myCircularQueue.Rear()).isEqualTo(5);

        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        assertThat(myCircularQueue.Front()).isEqualTo(4);
        assertThat(myCircularQueue.Rear()).isEqualTo(5);

        myCircularQueue.deQueue();
        assertThat(myCircularQueue.Front()).isEqualTo(5);
        assertThat(myCircularQueue.Rear()).isEqualTo(5);

        myCircularQueue.deQueue();
        assertThat(myCircularQueue.Front()).isEqualTo(-1);
        assertThat(myCircularQueue.Rear()).isEqualTo(-1);
    }

    @Test
    void isEmptyIsFullTest() {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(4);
        myCircularQueue.enQueue(5);

        assertThat(myCircularQueue.isEmpty()).isFalse();
        assertThat(myCircularQueue.isFull()).isTrue();

        myCircularQueue.deQueue();
        assertThat(myCircularQueue.isEmpty()).isFalse();
        assertThat(myCircularQueue.isFull()).isFalse();

        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        myCircularQueue.deQueue();
        assertThat(myCircularQueue.isEmpty()).isTrue();
        assertThat(myCircularQueue.isFull()).isFalse();
    }
}