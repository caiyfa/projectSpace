package psn.cyf.behavioral.pattern.interpreter;

public class Multiply  implements ArithmeticExpression{
    ArithmeticExpression left;
    ArithmeticExpression right;

    public Multiply(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Variables variables) {
        return left.interpret(variables)*right.interpret(variables);
    }
}
