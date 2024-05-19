package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/two-sum/description/">1.TwoSum</a>
 */
public class TwoSumTest {
    /*
     * nums 중에 두개를 골라서 합산한 결과가 target이 되는 index 구하기
     * - 정확히 하나의 답만 있음
     * - 동일 요소 중복사용 불가
     * - 2 <= nums.length <= 10,000
     */
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int leftIndex = 0; leftIndex < length - 1; leftIndex++) {
            for (int rightIndex = leftIndex + 1; rightIndex < length; rightIndex++) {
                if (nums[leftIndex] + nums[rightIndex] == target) {
                    return new int[]{leftIndex, rightIndex};
                }
            }
        }

        return new int[]{};
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] nums, int target, int[] result) {
        assertThat(twoSum(nums, target)).containsExactly(result);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }
}
