package psn.cyf.behavioral.pattern.interpreter;

public class Variable implements  ArithmeticExpression {
    @Override
    public int interpret(Variables variables) {
        return variables.get(this);
    }
}
