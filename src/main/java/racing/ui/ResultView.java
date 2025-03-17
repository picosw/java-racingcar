package racing.ui;

import racing.car.Car;
import racing.car.CarName;
import racing.car.Cars;

import java.util.List;

public class ResultView {

    private static final String POSITION_SYMBOL = "-";

    public static void printTitle() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void printTryCount(int tryCount) {
        System.out.println(tryCount + "회 시도 결과");
    }

    public static void printCars(Cars cars) {
        List<Integer> positions = cars.getPositions();
        List<CarName> carNames = cars.getCarNames();

        for (int i = 0; i < positions.size(); i++) {
            System.out.print(carNames.get(i) + " : ");
            printPosition(positions.get(i));
        }
        System.out.println();
    }

    private static void printPosition(int position) {
        System.out.println(POSITION_SYMBOL.repeat(position));
    }

    public static void printWinners(List<Car> winners) {
        for (int i = 0; i < winners.size(); i++) {
            CarName winnerName = winners.get(i).getName();
            System.out.print(winnerName);
            if (i != winners.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("가 최종 우승했습니다.");
        System.out.println();
    }
}
