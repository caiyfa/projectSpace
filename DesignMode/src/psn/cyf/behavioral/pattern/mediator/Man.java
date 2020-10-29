package psn.cyf.behavioral.pattern.mediator;

public class Man extends Person {
    public Man(String name, int age,   int requestAge, MarriageAgency marriageAgency) {
        super(name, age, Sex.MALE, requestAge, marriageAgency);
    }
}
