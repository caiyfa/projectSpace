package psn.cyf.structural.pattern.bridge;

public class Square extends AbstractShape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("使用"+color.getColor()+"画正方形");
    }
}
