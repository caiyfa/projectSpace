package psn.cyf.behavioral.pattern.chainOfResponsibility;

import java.util.concurrent.TimeUnit;

public class PlayerB extends Player {
    public PlayerB() {
        super("玩家B");
    }

    @Override
    public void handle() throws InterruptedException {
        System.out.println(this.getName()+"跳了个舞把球传给"+this.getNextPlayer().getName());
        //随机睡几毫秒
        TimeUnit.MILLISECONDS.sleep(RandomNum.random());
        this.next();
    }
}
