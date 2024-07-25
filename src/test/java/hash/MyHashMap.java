package hash;

import org.junit.jupiter.api.Test;

public class MyHashMap {

    static class Node {
        int key;
        int val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    final Node[] nodes = new Node[1000000];

    public MyHashMap() {
    }

    public void put(int key, int value) {
        // 해시 계산
        int index = calculateHash(key);

        // 버켓에 노드가 없으면 추가
        if (nodes[index] == null) {
            nodes[index] = new Node(key, value);
            return;
        }

        // 노드가 있으면 개별 체이닝 방식으로 되어 있을것이므로, 같은 key를 가졌다면 갱신, 체인에 없으면 맨 마지막 노드의 뒤에 붙임
        Node node = nodes[index];
        while (node != null) {
            if (node.key == key) {
                node.val = value;
                return;
            }

            if (node.next == null) {
                break;
            }

            node = node.next;
        }

        node.next = new Node(key, value);
    }

    private int calculateHash(int key) {
        return key % nodes.length;
    }

    public int get(int key) {
        int index = calculateHash(key);
        if (nodes[index] == null) {
            return -1;
        }

        Node node = nodes[index];
        while (node != null) {
            if (node.key == key) {
                return node.val;
            }
            node = node.next;
        }

        return -1;
    }

    public void remove(int key) {
        int index = calculateHash(key);
        if (nodes[index] == null) {
            return;
        }

        Node node = nodes[index];
        if (node.key == key) {  // 버킷의 맨 첫번째 노드라면
            if (node.next == null) {
                nodes[index] = null;
            } else {
                nodes[index] = node.next;
            }
        }

        Node prev = node;
        while (node != null) {
            if (node.key == key) {
                prev.next = node.next;
                return;
            }

            prev = node;
            node = node.next;
        }
    }

    @Test
    void test() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1);
        myHashMap.put(2, 2);
        System.out.println("myHashMap.get(1) = " + myHashMap.get(1));
    }
}
