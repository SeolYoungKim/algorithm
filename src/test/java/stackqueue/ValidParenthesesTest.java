package stackqueue;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/valid-parentheses/description/">20.ValidParentheses</a>
 */
public class ValidParenthesesTest {
    public boolean isValid(String s) {
        Map<Character, Character> parenthesesMap = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // character가 열림 괄호인 경우 스택에 push
            if (!parenthesesMap.containsKey(c)) {
                stack.push(c);
            // 닫힘 괄호인 경우, 스택이 비었거나 스택에서 pop한 것이 동일한 유형의 열림 괄호가 아니면 false
            } else if (stack.isEmpty() || parenthesesMap.get(c) != stack.pop()) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    @Test
    void test() {
        assertThat(isValid("()")).isTrue();
        assertThat(isValid("()[]{}")).isTrue();
        assertThat(isValid("(]")).isFalse();
        assertThat(isValid("({})")).isTrue();
        assertThat(isValid("[({}])")).isFalse();
    }
}
