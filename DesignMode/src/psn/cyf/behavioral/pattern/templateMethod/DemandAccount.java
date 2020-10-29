package psn.cyf.behavioral.pattern.templateMethod;

/**
 * 活期账户
 */
public class DemandAccount extends Account {
    @Override
    protected String getAccountType() {
        return "活期";
    }

    @Override
    protected double getInterestRate() {
        return 0.005D;
    }
}
