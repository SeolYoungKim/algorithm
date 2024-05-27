package array;

import static org.assertj.core.api.Assertions.assertThat;

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
        int[] result = new int[length];

        int left = 1;
        for (int i = 0; i < length; i++) {
            result[i] = left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
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
