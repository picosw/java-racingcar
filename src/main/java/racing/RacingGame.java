package racing;

import racing.car.Car;
import racing.car.Cars;
import racing.random.RandomNumberFactory;
import racing.ui.InputView;
import racing.ui.ResultView;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {

    private static final int MOVE_THRESHOLD = 4;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String[] carNames = InputView.inputCarNames();
        int tryCount = InputView.inputTryCount();

        Cars cars = new Cars(carNames);

        race(cars, tryCount);
        List<Car> winners = cars.getWinners();
        ResultView.printWinners(winners);
    }

    private static boolean movable() {
        return movable(RandomNumberFactory.createRandomNumber());
    }

    public static boolean movable(int number) {
        return number >= MOVE_THRESHOLD;
    }

    public static void race(Cars cars, int tryCount) {
        ResultView.printTitle();

        for (int i = 0; i < tryCount; i++) {
            ResultView.printTryCount(i + 1);
            cars.race(getMovables(cars.getSize()));
            ResultView.printCars(cars);
        }
    }

    private static List<Boolean> getMovables(int carCount) {
        List<Boolean> movables = new ArrayList<>();
        for (int i = 0; i < carCount; i++) {
            movables.add(movable());
        }
        return movables;
    }
}
