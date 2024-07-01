package stackqueue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/remove-duplicate-letters/description/">316.RemoveDuplicateLetters</a>
 *  ## 조건
 *  1. 모든 문자가 한번만 나타나야 함
 *  2. 사전순으로 가장 작은 결과를 반환해야 함 -> 😇
 *  ## 사전식 순서
 *  - 사전에서 가장 먼저 찾을 수 있는 순서
 */
public class RemoveDuplicateLettersTest {
    /**
     * <p>
     *     재귀 풀이 방법 : <img src="img.png" alt="Image description">
     *     <p>- 첫 번째 전체 집합 : a,b,c,d</p>
     *     <p>- 두 번째 전체 집합 : b,c,d</p>
     *     <p>아이디어 : suffix가 전체 집합을 나타낼 수 있어야 정답 단어에 추가될 수 있음</p>
     * </p>
     */
    public String removeDuplicateLettersWithRecursive(String s) {
        // 정렬된 문자열 집합 순회 : a -> b -> c -> ... 순서로 순회
        for (Character c : toSortedSet(s)) {
            // 해당 문자가 포함된 위치부터 접미사로 지정
            String suffix = s.substring(s.indexOf(c));  // a가 있다면 a부터 접미사

            // suffix 의 문자 집합이 전체 집합과 동일해야 함
            // -> 사전 순서 정렬을 위함
            // 전체 집합과 접미사 집합이 일치하면 해당 문자 정답에 추가하고 재귀 호출 진행
            if (toSortedSet(s).equals(toSortedSet(suffix))) {
                return c +  // 정답 문자에 추가
                        removeDuplicateLettersWithRecursive(suffix.replace(String.valueOf(c), ""));  // 정답 문자를 제외하고 이후 문자에 대해 재귀 수행
            }
        }

        return "";
    }

    // 문자열을 문자 단위로 집합에 저장 & 정렬된 상태로 저장 됨
    private Set<Character> toSortedSet(String s) {
        Set<Character> set = new TreeSet<>(Comparator.naturalOrder());
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public String removeDuplicateLettersWithStack(String s) {
        Map<Character, Integer> counter = new HashMap<>();  // 문자 개수를 계산하여 담아둘 변수 (사전 순서 구현을 위해 필요)
        Map<Character, Boolean> seen = new HashMap<>();  // 이미 처리한 문자 여부를 확인하기 위한 변수 (중복 제거를 위해 필요)
        Deque<Character> stack = new ArrayDeque<>();

        // 문자 별 개수 계산
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            // 현재 처리하는 문자는 카운터에서 -1
            counter.put(c, counter.get(c) - 1);

            // 이미 처리한 문자인 경우 건너 뜀
            if (seen.getOrDefault(c, false)) {
                continue;
            }

            // 스택에 있는 문자가 현재보다 더 뒤에 붙어야 할 문자인 경우 스택에서 제외
            while (!stack.isEmpty() && (stack.peek() > c) && (counter.get(stack.peek()) > 0)) {
                seen.put(stack.pop(), false);  // 처리하지 않은 걸로 표시
            }

            // 현재 문자를 스택에 삽입
            stack.push(c);

            // 현재 문자를 처리한 것으로 표시
            seen.put(c, true);
        }

        // 스택에 있는 문자를 큐 순서대로 추출하여 리턴
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }

    @Test
    void test() {
        assertThat(removeDuplicateLettersWithStack("bcabc")).isEqualTo("abc");
        assertThat(removeDuplicateLettersWithStack("cbacdcbc")).isEqualTo("acdb");
    }
}
