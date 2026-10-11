public record BinopNode (String operator, AST left, AST right) implements AST{

    /** Takes an AST and returns the double result of the mathematical expression */
    public double eval(){
        if (operator.equals("+")) {
            return left.eval() + right.eval();
        } else if (operator.equals("-")) {
            return right.eval() - left.eval();
        } else if (operator.equals("^")) {
            return Math.pow(right.eval(), left.eval());
        } else if (operator.equals("*")) {
            return left.eval() * right.eval();
        } else {
            return right.eval() / left.eval();
        }
    }

}
