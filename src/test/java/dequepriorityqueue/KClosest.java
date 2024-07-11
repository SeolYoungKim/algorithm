package dequepriorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosest {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(
                (int[] point) -> Math.sqrt(point[0] * point[0] + point[1] * point[1])
        ));

        for (int[] point : points) {
            pq.offer(point);
        }

        int[][] results = new int[k][2];
        for (int i = 0; i < k; i++) {
            results[i] = pq.poll();
        }

        return results;
    }
}
