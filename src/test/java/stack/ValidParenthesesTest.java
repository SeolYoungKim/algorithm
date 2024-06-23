package stack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/valid-parentheses/description/">20.ValidParentheses</a>
 */
public class ValidParenthesesTest {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            Character peek = stack.peek();
            if (peek != null && (peek == '(' && c == ')' || peek == '{' && c == '}' || peek == '[' && c == ']')) {
                stack.pop();
            } else {
                stack.push(c);
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
