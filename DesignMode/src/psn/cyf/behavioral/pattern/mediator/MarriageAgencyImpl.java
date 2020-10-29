package psn.cyf.behavioral.pattern.mediator;

import java.util.ArrayList;
import java.util.List;

public class MarriageAgencyImpl implements MarriageAgency {
    List<Man> man=new ArrayList<>();
    List<Woman> women=new ArrayList<>();

    @Override
    public void pair(Person person) {
        if(Sex.MALE.equals(person.sex)){
            for (Woman wo:women){
                if(wo.age==person.requestAge){
                    System.out.println(person.name+"和"+wo.name+"配对成功");
                return;
                }
            }
        }else {
            for (Man m:man){
                if(m.requestAge==person.age){
                    System.out.println(person.name+"和"+m.name+"配对成功");
                    return;
                }
            }
        }
        System.out.println("没有为"+person.name+"找到合适的对象。");
    }

    @Override
    public void register(Person person) {
        if(Sex.MALE.equals(person.sex)){
            man.add((Man)person);
        }else {
            women.add((Woman)person);
        }
    }
}
