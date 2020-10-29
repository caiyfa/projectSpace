package psn.cyf.structural.pattern.composite;

public class Employee implements ICompany {
    private String name;
    private String position;
    private int salary;

    public Employee(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        StringBuffer stringBuffer=new StringBuffer("名称：");
        stringBuffer.append(name).append(" 职位：").append(position)
                .append(" 薪水：").append(salary);
        return stringBuffer.toString();
    }
}
