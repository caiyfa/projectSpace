package psn.cyf.behavioral.pattern.mediator;

public class Person {
    String name;
    int age;
    Sex sex;
    /**
     * 配对要求，这里为了简化只要求年龄
     */
    int requestAge;
    MarriageAgency marriageAgency;

    public Person(String name, int age, Sex sex, int requestAge, MarriageAgency marriageAgency) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.requestAge = requestAge;
        this.marriageAgency = marriageAgency;
        marriageAgency.register(this);
    }
    public void findPartner(){
        marriageAgency.pair(this);
    }
}
