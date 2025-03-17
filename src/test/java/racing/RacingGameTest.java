package racing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racing.car.Car;
import racing.car.Cars;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RacingGameTest {

    private static final String[] CAR_NAMES = {"pobi", "crong", "honux"};

    @ParameterizedTest
    @CsvSource(value = {"1:false", "2:false", "3:false", "4:true", "5:true", "6:true", "7:true", "8:true", "9:true"}, delimiter = ':')
    void movableTest(int number, boolean expected) {
        assertThat(RacingGame.movable(number)).isEqualTo(expected);
    }

    @Test
    void raceTest() {

        Cars cars = new Cars(CAR_NAMES);
        cars.race(List.of(true, false, true));
        cars.race(List.of(true, true, true));
        cars.race(List.of(false, false, true));
        cars.race(List.of(false, true, true));
        cars.race(List.of(true, false, true));

        List<Integer> positions = cars.getPositions();

        assertThat(positions.get(0)).isEqualTo(3);
        assertThat(positions.get(1)).isEqualTo(2);
        assertThat(positions.get(2)).isEqualTo(5);
    }

    @Test
    void carNameTest() {
        assertThatThrownBy(() -> new Car("abcdef")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void winnerTest() {
        Cars cars = new Cars(CAR_NAMES);

        cars.race(List.of(true, true, true));
        cars.race(List.of(true, true, true));
        cars.race(List.of(false, true, true));
        cars.race(List.of(false, true, true));

        List<Car> winners = cars.getWinners();

        assertThat(winners.size()).isEqualTo(2);

        List<String> winnerNames = winners.stream()
                .map(car -> car.getName().toString())
                .collect(Collectors.toList());

        assertThat(winnerNames).contains("crong", "honux");
    }
}
