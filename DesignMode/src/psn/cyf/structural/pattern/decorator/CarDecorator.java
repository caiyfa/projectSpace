package psn.cyf.structural.pattern.decorator;

/**
 * 汽车抽象装饰
 */
public class CarDecorator  implements ICar{
    private ICar car;

    public CarDecorator(ICar car) {
        this.car = car;
    }

    @Override
    public void show() {
        car.show();
    }
}
