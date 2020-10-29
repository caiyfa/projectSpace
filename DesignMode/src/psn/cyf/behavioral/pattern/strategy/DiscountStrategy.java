package psn.cyf.behavioral.pattern.strategy;

/**
 * 打折策略
 */
public abstract class DiscountStrategy {
    /**
     * 书的价格
     */
    private double price = 0D;

    private int number=0;

    public DiscountStrategy(double price, int number) {
        this.price = price;
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
    public abstract double calculateDiscount();
}
