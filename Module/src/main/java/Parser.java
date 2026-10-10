public class Parser {

    /** Take as input a single string and return an AST of the RPN expression */
    public static AST parsePostFix(String str) {
        ArrayStack<AST> rpn = ArrayStack.empty_stack();
        String[] expression = getExpression(str);

        for (int x = 0; x < expression.length; x++) {
            if (expression[x].matches("\\d+")) {
                rpn.push(new NumNode(Double.parseDouble(expression[x])));
            } else if (expression[x].equals("+") || expression[x].equals("-") || expression[x].equals("*") || expression[x].equals("/") || expression[x].equals("^")) {
                AST second = rpn.pop();
                AST first = rpn.pop();
                rpn.push(new BinopNode(expression[x], first, second));
            }
        }
        return rpn.peek();
    }

    public static String[] getExpression(String str){
        String[] expression = str.split("\\s+");

        if (expression.length==0){
            throw new IllegalArgumentException("empty input");
        }

        int intCount=0;
        int opCount=0;
        for (String s : expression){
            if (s.matches("\\d+")){
                intCount++;
            } else
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                opCount++;
            } else {
                throw new IllegalArgumentException("invalid token");
            }
        }

        if (intCount-opCount<1){
            throw new IllegalArgumentException("insufficient operands");
        } else
        if (intCount-opCount>1){
            throw new IllegalArgumentException("too many operands");
        }

        return expression;
    }
}