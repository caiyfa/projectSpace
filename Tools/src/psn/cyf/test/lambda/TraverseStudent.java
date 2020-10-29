package psn.cyf.test.lambda;

import java.util.ArrayList;
import java.util.List;

public class TraverseStudent {
    public static void main(String[] args) {
        List<Student> students=new ArrayList<>();
        students.add(new Student("小明" ));
        students.add(new Student("小六" ));
        students.add(new Student("小张" ));
        students.add(new Student("小芳" ));
        List<String> names=students.stream().collect(ArrayList<String>::new,(list,student)->{
            list.add(student.getName());
        },(l1,l2)->{l1.addAll(l2);});
        for(String name :names){
            System.out.println(name);
        }

    }
}
