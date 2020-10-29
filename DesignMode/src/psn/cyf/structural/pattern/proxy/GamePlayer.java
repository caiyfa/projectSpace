package psn.cyf.structural.pattern.proxy;

public class GamePlayer  implements IGamePlayer{
    private String name;
    private int grade=0;

    public GamePlayer(String name) {
        this.name = name;

    }

    @Override
    public void killBoss() {
        System.out.println(this.name+"在打怪");
    }

    @Override
    public void upGrade() {
        grade++;
        System.out.println(this.name+"升了一级，当前等级:"+grade);
    }
}
