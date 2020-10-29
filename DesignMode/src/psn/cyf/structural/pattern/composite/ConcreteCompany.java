package psn.cyf.structural.pattern.composite;

import java.util.ArrayList;

public class ConcreteCompany implements ICompany {
    private ArrayList<ICompany> companyArrayList=new ArrayList<>();
    /**
     * 姓名
     */
    private String name ;
    /**
     * 职位
     */
    private String position;
    /**
     * 薪水
     */
    private int salary;

    public ConcreteCompany(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public void add(ICompany company){
        this.companyArrayList.add(company);
    }
    private void remove(ICompany company){
        this.companyArrayList.remove(company);
    }
    public ArrayList<ICompany> getChild(){
        return this.companyArrayList;
    }

    @Override
    public String getInfo() {
      StringBuffer stringBuffer=new StringBuffer("名称：");
      stringBuffer.append(name).append(" 职位：").append(position)
              .append(" 薪水：").append(salary);
      return stringBuffer.toString();
    }


}
