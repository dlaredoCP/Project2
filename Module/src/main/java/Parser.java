public class Parser {

    /** Take as input a single string and return an AST of the RPN expression */
    public static AST parsePostFix(String str) {
        ArrayStack<AST> rpn = ArrayStack.empty_stack();
        String[] expression = getExpression(str, false);

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

    /** Accepts a string and returns a String[] of its tokens*/
    public static String[] getExpression(String str, boolean postFix){
        if (str.isBlank()){
            throw new IllegalArgumentException("empty input");
        }

        String[] expression = str.split("\\s+");

        int intCount=0;
        int opCount=0;
        for (String s : expression){
            if (s.matches("\\d+")){
                intCount++;
            } else
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                opCount++;
            } else
            if (postFix && (s.equals("(") || s.equals(")"))){
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

    /** Takes an infix expression and converts it to an RPN expression via the Shunting-yard algorithm */
    public static AST parseInfix(String str){
        ArrayStack<AST> output = ArrayStack.empty_stack();
        ArrayStack<String> operators = ArrayStack.empty_stack();
        String[] expression = getExpression(str, true);

        for (String s : expression) {
            if (s.matches("\\d+")){
                output.push(new NumNode(Double.parseDouble(s)));
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                if (!operators.isEmpty() && (precedence(s)<precedence(operators.peek()) || (precedence(s)==precedence(operators.peek()) && leftAssoc(s)))){
                    output.push(new BinopNode(operators.pop(), output.pop(), output.pop()));
                } else {
                    operators.push(s);
                }
            } else if (s.equals("(")){
                operators.push(s);
            } else if (s.equals(")")){
                while (!operators.peek().equals("(")){
                    output.push(new BinopNode(operators.pop(), output.pop(), output.pop()));
                }
                if (operators.peek().equals("(")){
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("mismatched close paren");
                }
            }
        }
        while (!operators.isEmpty()){
            output.push(new BinopNode(operators.pop(), output.pop(), output.pop()));
        }
        return output.peek();
    }

    public static int precedence(String str){
        if (str.equals("^")){
            return 3;
        } else
        if (str.equals("/") || str.equals("*")){
            return 2;
        } else
        if (str.equals("+") || str.equals("-")){
            return 1;
        } else
        if (str.equals("(")){
            return 0;
        } else {
            throw new IllegalArgumentException("invalid token");
        }
    }

    public static boolean leftAssoc(String str){
        return switch (str) {
            case "^" -> false;
            case "/", "*", "+", "-"-> true;
            default -> throw new IllegalArgumentException("invalid token");
        };
    }
}