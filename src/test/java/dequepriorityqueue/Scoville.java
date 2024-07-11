package dequepriorityqueue;

import java.util.PriorityQueue;

// 스코빌 지수
public class Scoville {
    public int scoville(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }

        while (pq.size() > 1) { // 섞을 음식이 최소 2개 이상일 때만 반복
            if (pq.peek() >= K) { // 가장 작은 값이 K 이상인지 확인
                return answer;
            }
            int first = pq.poll(); // 가장 맵지 않은 음식
            int second = pq.poll(); // 두 번째로 맵지 않은 음식
            int mixed = first + (second * 2); // 새로운 음식의 스코빌 지수 계산
            pq.offer(mixed); // 새로운 음식 큐에 추가
            answer++;
        }

        // 마지막으로 남은 음식이 K 이상인지 확인
        if (pq.peek() >= K) {
            return answer;
        } else {
            return -1; // K 이상으로 만들 수 없는 경우
        }
    }
}
