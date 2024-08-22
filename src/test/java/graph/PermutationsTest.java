package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/permutations/">link</a>
 */
public class PermutationsTest {
    /**
     * 순열이란, 결국 모든 가능한 경우를 그래프로 나열한 결과이다
     * <pre>
     *     <img src="img.png">
     * </pre>
     */
    public List<List<Integer>> permute(int[] nums) {
        // 결과 저장 리스트 선언
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> numsList = Arrays.stream(nums).boxed().toList();
        dfs(results, new ArrayList<>(), numsList);
        return results;
    }

    public void dfs(List<List<Integer>> results, List<Integer> prevElements, List<Integer> elements) {
        // 리프 노드에 도달 시 결과 리스트에 추가
        if (elements.isEmpty()) {
            results.add(new ArrayList<>(prevElements));  // 저장 시점에 복사해야함
        }

        // 전달 받은 엘리먼트 모두 탐색
        for (Integer element : elements) {
            // 전달받은 엘리먼트에서 현재 엘리먼트 제외
            List<Integer> nextElements = new ArrayList<>(elements);
            nextElements.remove(element);

            // prevElements에 현재 엘리먼트 추가
            prevElements.add(element);
            // dfs
            dfs(results, prevElements, nextElements);
            // prevElements에서 현재 엘리먼트 삭제
            prevElements.remove(element);
        }
    }
}
