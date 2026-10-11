public record NumNode (double num) implements AST{

    /** Takes an AST and returns the double result of the mathematical expression */
    public double eval(){
        return num;
    }

}
