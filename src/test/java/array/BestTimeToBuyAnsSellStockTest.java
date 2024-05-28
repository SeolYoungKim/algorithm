package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/">121.BestTimeToBuyAnsSellStock</a>
 */
public class BestTimeToBuyAnsSellStockTest {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[] lowPoint = new int[length];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            min = Math.min(min, prices[i]);
            lowPoint[i] = min;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, prices[i] - lowPoint[i]);
        }

        return max;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] prices, int expected) {
        assertThat(maxProfit(prices)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{7, 1, 5, 3, 6, 4}, 5),
                Arguments.of(new int[]{7, 2, 5, 3, 4, 1, 6}, 5),
                Arguments.of(new int[]{7, 6, 4, 3, 1}, 0)
        );
    }
}
