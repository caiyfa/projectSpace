package psn.cyf.behavioral.pattern.mediator;

public class Woman extends Person {
    public Woman(String name, int age,   int requestAge, MarriageAgency marriageAgency) {
        super(name, age, Sex.FEMALE, requestAge, marriageAgency);
    }
}
