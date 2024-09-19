package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 77
 * 전체 수 n을 입력받아 k개의 조합 리턴하는 문제
 */
public class Combinations {
    /**
     * 조합의 수 = n!/k!(n-k)! - [1,2,3]과 [3,2,1]은 서로 동일하다
     */
    public void dfs(List<List<Integer>> results, LinkedList<Integer> elements, int n, int start, int k) {
        // k번째 노드에 도달하면 결과에 추가
        if (k == 0) {
            results.add(elements.stream().toList());
            return;
        }

        // 현재 엘리먼트 이후의 엘리먼트를 탐색
        for (int i = start; i <= n; i++) {
            // 전달받은 엘리먼트에 더해 현재 엘리먼트 추가
            elements.add(i);
            // dfs
            dfs(results, elements, n, i + 1, k - 1);
            // 돌아온 후 현재 엘리먼트 삭제
            elements.removeLast();
        }
    }
}
