package psn.cyf.behavioral.pattern.chainOfResponsibility;

import java.util.concurrent.TimeUnit;

public class PlayerC extends Player {
    public PlayerC( ) {
        super("玩家C");
    }

    @Override
    public void handle() throws InterruptedException {
        System.out.println(this.getName()+"亲了自己的脚，把球传给了"+this.getNextPlayer().getName());
        //随机睡几毫秒
        TimeUnit.MILLISECONDS.sleep(RandomNum.random());
        this.next();
    }
}
