package psn.cyf.behavioral.pattern.interpreter;

public class Subtract implements ArithmeticExpression {
    ArithmeticExpression left;
    ArithmeticExpression right;

    public Subtract(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Variables variables) {
        return left.interpret(variables)-right.interpret(variables);
    }
}
