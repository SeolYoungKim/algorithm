package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.Test;

/**
 * 인접 리스트를 이용해 그래프를 표현하고, 이를 이용해 DFS, BFS 구현
 */
public class DfsAndBfsWithAdjacencyListTest {
    private final Map<Integer, List<Integer>> graph = Map.of(
            1, List.of(2, 3, 4),
            2, List.of(5),
            3, List.of(5),
            4, List.of(),
            5, List.of(6, 7),
            6, List.of(),
            7, List.of(3)
    );

    /**
     * 정점 v의 모든 Adjacent Directed Edge 들을 반복
     * @param discovered 방문했던 정점 목록
     */
    public List<Integer> recursiveDfs(int v, List<Integer> discovered) {
        // 현재 노드 저장
        discovered.add(v);

        // 주변 노드 반복
        for (Integer w : graph.getOrDefault(v, List.of())) {
            // 아직 처리되지 않은 노드인 경우 dfs 수행
            if (!discovered.contains(w)) {
                discovered = recursiveDfs(w, discovered);
            }
        }

        // 모든 깊이를 탐색할 경우 리턴
        return discovered;
    }

    @Test
    void recursiveDfsTest() {
        List<Integer> r = new ArrayList<>();
        System.out.println("result = " + recursiveDfs(1, r));
    }

    public List<Integer> iterativeDfs(int v) {
        // 결과 노드를 저장할 리스트 선언
        List<Integer> discovered = new ArrayList<>();
        // 스택 선언
        Deque<Integer> stack = new ArrayDeque<>();
        // 현재 노드를 스택에 삽입
        stack.push(v);
        // 스택이 빌 때 까지 반복
        while (!stack.isEmpty()) {
            // 스택에서 추출
            v = stack.pop();
            // 이미 방문한 노드가 아닌 경우 방문 노드에 추가하고 주변 노드들을 모두 스택에 삽입
            if (!discovered.contains(v)) {
                discovered.add(v);
                //현재 노드에서 연결된 모든 주변 노드를 스택에 삽입
                for (Integer w : graph.get(v)) {
                    stack.push(w);
                }
            }
        }
        // 더이상 탐색할 노드가 없을 경우 리턴
        return discovered;
    }

    @Test
    void iterativeDfsTest() {
        System.out.println("result = " + iterativeDfs(1));
    }

    public List<Integer> iterativeBfs(int startV) {
        // 결과 노드를 저장할 리스트 선언
        List<Integer> discovered = new ArrayList<>();
        // 시작 노드 삽입
        discovered.add(startV);
        // 큐 선언
        Queue<Integer> queue = new LinkedList<>();
        // 시작 노드 삽입
        queue.add(startV);
        // 큐가 빌 때 까지 반복
        while (!queue.isEmpty()) {
            // 큐에서 추출
            int v = queue.poll();
            // 현재 노드에서 연결된 모든 주변 노드를 큐에 삽입
            for (Integer w : graph.get(v)) {
                // 이미 방문한 노드가 아닐 경우 방문 노드에 추가하고, 주변 노드 모두 큐에 삽입
                if (!discovered.contains(w)) {
                    discovered.add(w);
                    queue.add(w);
                }
            }
        }
        // 더이상 탐색할 노드가 없을 경우 리턴
        return discovered;
    }

    @Test
    void iterativeBfsTest() {
        System.out.println(iterativeBfs(1));
    }
}
