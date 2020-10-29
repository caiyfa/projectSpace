package psn.cyf.behavioral.pattern.mediator;

public interface MarriageAgency {

    /**
     * 配对
     * @param person
     */
    void pair(Person person);

    /**
     * 注册
     * @param person
     */
    void register(Person person);
}
