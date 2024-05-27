package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/product-of-array-except-self/">561.ProductOfArrayExceptSelf</a>
 */
public class ProductOfArrayExceptSelfTest {
    /*
     * answer[i]는 nums[i]를 제외한 나머지 수들의 곱이 된다
     * O(N)으로 구성해야 한다
     * 곱은 int임이 보장된다
     * 나누기 연산은 사용하면 안됨
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        // 인덱스 기준으로 왼쪽, 오른쪽 따로 계산
        // left
        Map<Integer, Integer> leftMap = new HashMap<>();
        leftMap.put(0, 1);

        int left = 1;
        for (int i = 1; i < length; i++) {
            left *= nums[i - 1];
            leftMap.put(i, left);
        }

        // right
        Map<Integer, Integer> rightMap = new HashMap<>();
        rightMap.put(length - 1, 1);

        int right = 1;
        for (int i = length - 2; i >= 0; i--) {
            right *= nums[i + 1];
            rightMap.put(i, right);
        }

        for (int i = 0; i < length; i++) {
            nums[i] = leftMap.get(i) * rightMap.get(i);
        }

        return nums;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] nums, int[] expected) {
        assertThat(productExceptSelf(nums)).containsExactlyInAnyOrder(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}),
                Arguments.of(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0})
        );
    }
}
