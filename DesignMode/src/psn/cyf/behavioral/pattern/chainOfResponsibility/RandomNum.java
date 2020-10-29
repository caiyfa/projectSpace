package psn.cyf.behavioral.pattern.chainOfResponsibility;

import java.util.Random;

public class RandomNum {
    public static int random(){
        return new Random().ints(1,8).findFirst().getAsInt();
    }
}
