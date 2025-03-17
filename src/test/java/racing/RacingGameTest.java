package racing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racing.car.Car;
import racing.car.Cars;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RacingGameTest {

    private static final String[] CAR_NAMES = {"pobi", "crong", "honux"};

    @ParameterizedTest
    @CsvSource(value = {"1:false", "2:false", "3:false", "4:true", "5:true", "6:true", "7:true", "8:true", "9:true"}, delimiter = ':')
    void movableTest(int number, boolean expected) {
        Car car = new Car("pobi");
        assertThat(car.movable(number)).isEqualTo(expected);
    }

    @Test
    void raceTest() {
        int tryCount = 5;
        Cars cars = new Cars(CAR_NAMES);
        cars.race(tryCount);
        List<Integer> positions = cars.getPositions();

        for (Integer position : positions) {
            assertThat(position).isBetween(0, tryCount);
        }
    }

    @Test
    void carNameTest() {
        assertThatThrownBy(() -> new Car("abcdef")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void winnerTest() {
        int tryCount = 5;
        Cars cars = new Cars(CAR_NAMES);

        cars.race(tryCount);
        int maxPosition = cars.getMaxPosition();
        List<Car> winners = cars.getWinners();

        for (Car winner : winners) {
            assertThat(winner.getPosition()).isEqualTo(maxPosition);
        }
    }
}
