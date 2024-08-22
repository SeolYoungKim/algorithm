package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/">link</a>
 */
public class LetterCombinationsOfAPhoneNumberTest {
    private final Map<Character, List<Character>> graph = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z')
    );

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits.isEmpty()) {
            return results;
        }

        dfs(results, digits, 0, new StringBuilder());
        return results;
    }

    public void dfs(List<String> result, String digits, int index, StringBuilder path) {
        // 끝까지 탐색했다면 결과를 저장 후 리턴
        if (path.length() == digits.length()) {
            result.add(path.toString());
            return;
        }

        // 현재 자리 숫자에 해당하는 모든 문자열 탐색
        for (Character c : graph.get(digits.charAt(index))) {
            // 현재 자리 + 1, 지금가지 구성된 문자열 path 이용 재귀 dfs
            dfs(result, digits, index + 1, new StringBuilder(path).append(c));
        }
    }
}
