package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/trapping-rain-water/description/">42.TrappingRainWater</a>
 */
public class TrappingRainWaterTest {
    /*
     * 각 막대의 너비가 1인 elevation map을 나타내는 음이 아닌 정수 N이 주어졌을 때
     * 비가 내린 후 얼마나 많은 물을 가둘 수 있는지를 계산한다.
     */
    public int trapWithStack(int[] height) {
        int result = 0;

        Deque<Integer> stack = new ArrayDeque<>();// 3, 4, 5
        for (int i = 0; i < height.length; i++) {
            // 변곡점(현재 높이가 이전 높이보다 높은 지점)을 만나는 경우
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 스택에서 꺼낸다
                Integer top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }

                // 스택의 마지막 위치까지를 거리로 계산
                int distance = i - stack.peek() - 1;

                // 현재 높이 또는 스택의 마지막 위치 높이 중, 낮은 값에 방금 꺼낸 높이의 차이를 물 높이로 지정
                int water = Math.min(height[i], height[stack.peek()]) - height[top];  // 낮은 쪽에 맞춰서 물 높이 계산

                // 물이 쌓이는 양 = 거리 * 물 높이
                result += distance * water;
            }

            stack.push(i);
        }

        return result;
    }

    public int trap(int[] height) {
        int result = 0;

        int leftIdx = 0;
        int rightIdx = height.length - 1;

        int leftMax = height[leftIdx];
        int rightMax = height[rightIdx];

        // 투 포인터가 서로 겹칠 때까지 반복
        while (leftIdx < rightIdx) {
            leftMax = Math.max(height[leftIdx], leftMax);
            rightMax = Math.max(height[rightIdx], rightMax);

            // 더 높은 쪽을 향해 투 포인터를 이동
            if (leftMax <= rightMax) {
                // 왼쪽 막대 최대 높이와의 차이를 더하고 왼쪽 포인터 이동
                result += (leftMax - height[leftIdx]);
                leftIdx++;
            } else {
                // 오른쪽 막대 최대 높이와의 차이를 더하고 왼쪽 포인터 이동
                result += (rightMax - height[rightIdx]);
                rightIdx--;
            }
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] height, int result) {
        assertThat(trap(height)).isEqualTo(result);
        assertThat(trapWithStack(height)).isEqualTo(result);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6),
                Arguments.of(new int[]{4, 2, 0, 3, 2, 5}, 9),
                Arguments.of(new int[]{4, 2, 3}, 1),
                Arguments.of(new int[]{4, 2, 3, 0, 0, 0, 0, 0, 1, 2}, 12)
        );
    }
}
