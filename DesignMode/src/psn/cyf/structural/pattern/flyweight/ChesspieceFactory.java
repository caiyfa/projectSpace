package psn.cyf.structural.pattern.flyweight;

public class ChesspieceFactory {
    static Chesspiece WHITE = new ChesspieceFlyweight("白");
    static Chesspiece BLACK = new ChesspieceFlyweight("黑");
public static Chesspiece getChesspiece(String color){
    //棋子只有两种颜色所以这里就不用Map来存放了
    if("白".equals(color)){
        return WHITE;
    }else if("黑".equals(color)){
        return BLACK;
    }
    return null;
}

}
