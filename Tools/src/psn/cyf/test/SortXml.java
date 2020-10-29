package psn.cyf.test;

public class SortXml {
    public static void main(String[] args) {
        Demo demo=new Demo();
        System.out.println(demo.getName());
        System.out.println(demo.name);
    }
}
class Demo{
    public String name;

    public String getName() {
        if(name==null){
            return name="CS";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
