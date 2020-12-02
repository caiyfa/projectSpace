package psn.cyf.test;

import java.util.Date;

public class TestReflact {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(Person.class.getDeclaredField("birthday").getType().getName());
    }
}
class Person{
    private Date  birthday;
    private Integer age;
    private String name;

}