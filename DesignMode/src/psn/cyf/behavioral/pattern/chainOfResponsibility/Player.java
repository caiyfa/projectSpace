package psn.cyf.behavioral.pattern.chainOfResponsibility;

/**
 * 击鼓传花
 *      -游戏参与者
 */
public abstract class Player {
    private String name;
    public Boolean hasFlower=false;
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Player nextPlayer;
    public abstract void handle() throws InterruptedException;
    public void setNextPlayer(Player nextPlayer){
        this.nextPlayer = nextPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    /**
     * 传给下一个
     */
    public void next() throws InterruptedException {
        this.hasFlower=false;
        nextPlayer.hasFlower=true;
        if(ClientDemo.isStop){
            return;
        }
        nextPlayer.handle();
    }


}
