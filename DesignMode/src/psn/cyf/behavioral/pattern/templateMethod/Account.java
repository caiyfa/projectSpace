package psn.cyf.behavioral.pattern.templateMethod;

public abstract class Account {
    private String accountNumber;

    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return 返回账户类型
     */
    protected abstract String getAccountType();

    /**
     * @return 返回账户对应利息
     */
    protected abstract double getInterestRate();

    /**
     * 返回账户余额
     * @param accountType 账户类型
     * @param accountNumber 账户
     * @return
     */
    public double calculateAmount(String accountType,String accountNumber){
        //这里象征性的返回一个空账户余额，应到数据库中查询
        return 205624008.64D;
    }
    public double calculateInterest(){
        String type=getAccountType();
        double interestRate=getInterestRate();
        double amount=calculateAmount(type,accountNumber);
        return amount*interestRate;

    }

}
