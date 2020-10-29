package psn.cyf.behavioral.pattern.chainOfResponsibility;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerA extends Player {
    public PlayerA() {
        super("玩家A");
    }

    @Override
    public void handle() throws InterruptedException {
        System.out.println(this.getName()+"唱了首歌把球传给"+this.getNextPlayer().getName());
        //随机睡几毫秒
        TimeUnit.MILLISECONDS.sleep(RandomNum.random());
        this.next();
    }
}
