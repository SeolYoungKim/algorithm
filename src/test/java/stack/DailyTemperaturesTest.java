package stack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode.com/problems/daily-temperatures/description/">739.DailyTemperatures</a>
 */
public class DailyTemperaturesTest {
    /**
     * <img src="img_1.png" alt="Image description">
     * <p>
     *     <h3>Idea</h3>
     *     <li>현재 인덱스에 해당하는 온도와, 스택에 들어있는 마지막 인덱스의 온도를 비교한다</li>
     *     <li>현재 인덱스에 해당하는 온도가 더 높으면, 스택에 들어있는 마지막 인덱스의 결과를 업데이트 한다. (현재 인덱스 - 스택 인덱스)가 구하고자 하는 값이다.</li>
     * </p>
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] results = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < length; i++) {
            // 현재 온도가 스택에 있는 온도보다 높을 경우 스택에 있는 결과를 꺼내서 업데이트
            int currentTemperature = temperatures[i];
            while (!stack.isEmpty() && currentTemperature > temperatures[stack.peek()]) {
                int last = stack.pop();
                results[last] = i - last;
            }
            stack.push(i);
        }

        return results;
    }

    @Test
    void test() {
        assertThat(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})).containsExactly(1, 1, 4, 2, 1, 1, 0, 0);
        assertThat(dailyTemperatures(new int[]{30, 40, 50, 60})).containsExactly(1, 1, 1, 0);
        assertThat(dailyTemperatures(new int[]{30, 60, 90})).containsExactly(1, 1, 0);
    }
}
