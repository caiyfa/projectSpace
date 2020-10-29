package psn.cyf.behavioral.pattern.strategy;

public class FixDiscountStrategy extends DiscountStrategy {
    public FixDiscountStrategy(double price, int number) {
        super(price, number);
    }

    /**
     * @return 固定折扣
     */
    @Override
    public double calculateDiscount() {
        return getNumber()*1;
    }
}
