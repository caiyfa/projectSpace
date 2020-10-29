package psn.cyf.structural.pattern.proxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer player;
    private int killer=0;

    public GamePlayerProxy(IGamePlayer player) {
        this.player = player;

    }
    private void log(){
        System.out.print("打怪时间："+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        killer++;
    }

    @Override
    public void killBoss() {
        this.log();
        player.killBoss();
    }

    @Override
    public void upGrade() {
        System.out.println("击杀了"+killer+"头怪物");
         player.upGrade();
        count();
    }
    private void count(){
        System.out.println("花费时间："+ new Random().ints(1,10).findFirst().getAsInt());
    }

}
