package racing.car;

public class Car {

    private int position;
    private CarName name;

    public Car(String name) {
        this.position = 0;
        this.name = new CarName(name);

    }

    public void move() {
        position++;
    }

    public CarName getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner(int maxPosition) {
        return position == maxPosition;
    }
}
