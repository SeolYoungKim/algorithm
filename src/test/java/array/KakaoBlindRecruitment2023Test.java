package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/150370">개인정보 수집 유효기간</a>
 */
public class KakaoBlindRecruitment2023Test {
    /*
     * - 개인정보는 약관이 있다 (약관마다 유효기간이 다르다)
     * - 오늘 날짜 기준으로 파기해야 할 개인 정보를 구한다
     * - today : 오늘 날짜
     * - terms : 약관 종류, 유효 기간
     * - privacies : 번호, 개인정보 수집 일자, 약관 종류
     */
    public int[] solution(String today, String[] terms, String[] privacies) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate currentDate = LocalDate.parse(today, dateTimeFormatter);

        // 약관 Map
        Map<String, Integer> termsMap = Arrays.stream(terms)
                .map(term -> term.split(" "))
                .collect(Collectors.toUnmodifiableMap(
                        term -> term[0],
                        term -> Integer.parseInt(term[1])
                ));

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] split = privacy.split(" ");
            LocalDate collectionDate = LocalDate.parse(split[0], dateTimeFormatter);
            Integer expirationPeriod = termsMap.get(split[1]);

            LocalDate expirationDate = collectionDate.plusMonths(expirationPeriod);
            if (currentDate.isEqual(expirationDate) || currentDate.isAfter(expirationDate)) {
                results.add(i + 1);
            }
        }

        return results.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(String today, String[] terms, String[] privacies, int[] expected) {
        assertThat(solution(today, terms, privacies)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}, new int[]{1, 3}),
                Arguments.of("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}, new int[]{1, 4, 5})
        );
    }
}
