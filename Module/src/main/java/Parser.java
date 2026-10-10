public class Parser {

    /** Take as input a single string and return an AST of the RPN expression*/
    public static AST parsePostFix(String str){
        if (str.isEmpty()){
            throw new IllegalArgumentException("Empty input");
        }
        ArrayStack<AST> rpn = ArrayStack.empty_stack();
        ArrayStack<String> strStack = ArrayStack.empty_stack();
        String[] arr = str.split("\\s+");
        int opCount = 0;
        int intCount = 0;

        for (String s : arr){
            strStack.push(s);
            if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("^")|| s.equals("*")) {
                opCount++;
            } else
            if (s.matches("\\d+")){
                intCount++;
            } else {
                throw new IllegalArgumentException("Invalid token");
            }
        }

        if (intCount - opCount < 1){
            throw new IllegalArgumentException("Insufficient operands");
        } else
        if (intCount - opCount > 1){
            throw new IllegalArgumentException("Too many operands");
        }
        int strLen = strStack.size();
        for (int x=0; x<strLen; x++){
            if (strStack.isEmpty()){
                break;
            } else
            if (strStack.peek().matches("\\d+")){
                Double thing = Double.parseDouble(strStack.pop());
                rpn.push(new NumNode(thing));
            } else
            if (strStack.peek().equals("+") || strStack.peek().equals("-") || strStack.peek().equals("/") || strStack.peek().equals("^")|| strStack.peek().equals("*")){
                String op = strStack.pop();
                String next1 = strStack.pop();
                String next2 = strStack.pop();
                if (next1.matches("\\d+") && next2.matches("\\d+")){
                    rpn.push(new BinopNode(op,new NumNode(Double.parseDouble(next1)),new NumNode(Double.parseDouble(next2))));
                } else
                if (!next1.matches("\\d+") && next2.matches("\\d+")) {
                    rpn.push(new BinopNode(op,new BinopNode(next1, null, null),new NumNode(Double.parseDouble(next2))));
                } else
                if (next1.matches("\\d+") && !next2.matches("\\d+")) {
                    rpn.push(new BinopNode(op,new BinopNode(next1, null, null),new NumNode(Double.parseDouble(next1))));
                } else{
                    rpn.push(new BinopNode(op,new BinopNode(next1, null, null),new BinopNode(next2, null, null)));
                }
            }
        }

        return rpn.peek();
    }

}