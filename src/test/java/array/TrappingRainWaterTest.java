package array;

import static org.assertj.core.api.Assertions.assertThat;

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
