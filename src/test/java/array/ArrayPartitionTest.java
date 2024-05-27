package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/array-partition/description/">561.ArrayPartition</a>
 */
public class ArrayPartitionTest {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                result += nums[i];
            }
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] nums, int expected) {
        assertThat(arrayPairSum(nums)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{6, 2, 6, 5, 1, 2}, 9),
                Arguments.of(new int[]{1, 4, 3, 2}, 4)
        );
    }
}
