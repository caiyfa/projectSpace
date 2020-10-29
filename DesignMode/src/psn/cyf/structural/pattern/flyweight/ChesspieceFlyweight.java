package psn.cyf.structural.pattern.flyweight;

public class ChesspieceFlyweight implements Chesspiece {
    /**
     * 内部属性棋子的种类有限只有黑白两种
     * 符合享元模式基础条件
     */
    private String color;

    public ChesspieceFlyweight(String color) {
        this.color = color;
    }

    @Override
    public void put(int x, int y) {
        System.out.println("在（"+x+","+y+"）位置放置了一个"+color+"子");
    }
}
