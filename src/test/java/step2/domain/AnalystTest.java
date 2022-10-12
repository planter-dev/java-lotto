package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AnalystTest {
    static Stream<Arguments> getCountByRankOneLottoParam() {
        LottoNumber winNumber = new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)))),
                        winNumber,
                        Map.of(0, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 8, 9, 10, 11, 12)))),
                        winNumber,
                        Map.of(1, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 9, 10, 11, 12)))),
                        winNumber,
                        Map.of(2, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12)))),
                        winNumber,
                        Map.of(3, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 11, 12)))),
                        winNumber,
                        Map.of(4, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 12)))),
                        winNumber,
                        Map.of(5, 1L)
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))),
                        winNumber,
                        Map.of(6, 1L)
                )
        );
    }

    @DisplayName("로또를 한개산 경우 맞춘수별 개수 계산")
    @ParameterizedTest(name = "{displayName} {index} 로또번호: {0} |통계값: {1}")
    @MethodSource("getCountByRankOneLottoParam")
    void getCountByRankOneLotto(List<LottoNumber> gambleHistory, LottoNumber winNumber, Map<Integer, Long> expectedCountByRank) {
        Analyst analyst = new Analyst(gambleHistory, winNumber);
        Map<Integer, Long> countByRank = analyst.getCountByRank();
        assertThat(countByRank).isEqualTo(expectedCountByRank);
    }

    static Stream<Arguments> getCountByRankMultiLottoParam() {
        LottoNumber winNumber = new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)))
                        ),
                        winNumber,
                        Map.of(0, 2L)
                ),
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12)))
                        ),
                        winNumber,
                        Map.of(3, 2L)
                ),
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))
                        ),
                        winNumber,
                        Map.of(
                                0, 1L,
                                1, 1L,
                                2, 1L,
                                3, 1L,
                                4, 1L,
                                5, 1L,
                                6, 1L
                        )
                )
        );
    }

    @DisplayName("로또를 두개이상 산 경우 맞춘수별 개수 계산")
    @ParameterizedTest(name = "{displayName} {index} 로또번호: {0} |통계값: {1}")
    @MethodSource("getCountByRankMultiLottoParam")
    void getCountByRankMultiLotto(List<LottoNumber> gambleHistory, LottoNumber winNumber, Map<Integer, Long> expectedCountByRank) {
        Analyst analyst = new Analyst(gambleHistory, winNumber);
        Map<Integer, Long> countByRank = analyst.getCountByRank();
        assertThat(countByRank).isEqualTo(expectedCountByRank);
    }

    static Stream<Arguments> revenueRatioOneLottoParam() {
        LottoNumber winNumber = new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)))),
                        winNumber,
                        0.0f
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12)))),
                        winNumber,
                        5.0f
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 11, 12)))),
                        winNumber,
                        50.0f
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 12)))),
                        winNumber,
                        1500.0f
                ),
                Arguments.arguments(
                        List.of(new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))),
                        winNumber,
                        2000000.0f
                )
        );
    }

    @DisplayName("로또를 한개산 경우 수익률 계산")
    @ParameterizedTest(name = "{displayName} {index} 연산자: {0} |연산대상: {1}")
    @MethodSource("revenueRatioOneLottoParam")
    void revenueRatioOneLotto(List<LottoNumber> gambleHistory, LottoNumber winNumber, float expectedRatio) {
        Analyst analyst = new Analyst(gambleHistory, winNumber);
        float revenueRatio = analyst.revenueRatio(analyst.getCountByRank());
        assertThat(revenueRatio).isEqualTo(expectedRatio);

    }

    static Stream<Arguments> revenueRatioMultiLottoParam() {
        LottoNumber winNumber = new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12)))
                        ),
                        winNumber,
                        0.0f
                ),
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12)))
                        ),
                        winNumber,
                        5.0f
                ),
                Arguments.arguments(
                        List.of(
                                new LottoNumber(new ArrayList<>(List.of(7, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 8, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 9, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 11, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 12))),
                                new LottoNumber(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))
                        ),
                        winNumber,
                        2001555.0f / 7
                )
        );
    }

    @DisplayName("로또를 두개이상 산 경우 수익률 계산")
    @ParameterizedTest(name = "{displayName} {index} 연산자: {0} |연산대상: {1}")
    @MethodSource("revenueRatioMultiLottoParam")
    void revenueRatioMultiLotto(List<LottoNumber> gambleHistory, LottoNumber winNumber, float expectedRatio) {
        Analyst analyst = new Analyst(gambleHistory, winNumber);
        float revenueRatio = analyst.revenueRatio(analyst.getCountByRank());
        assertThat(revenueRatio).isEqualTo(expectedRatio);

    }
}