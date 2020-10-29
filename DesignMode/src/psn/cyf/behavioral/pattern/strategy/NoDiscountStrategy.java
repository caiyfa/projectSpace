package psn.cyf.behavioral.pattern.strategy;

public class NoDiscountStrategy extends DiscountStrategy {
    public NoDiscountStrategy(double price, int number) {
        super(price, number);
    }

    @Override
    public double calculateDiscount() {
        return 0;
    }
}
