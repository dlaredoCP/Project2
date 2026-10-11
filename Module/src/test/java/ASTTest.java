import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ASTTest {

    @Test
    void eval(){
        assertEquals(14.0, Parser.parseInfix("2 * ( 3 + 4 )").eval());
        assertEquals(2.0, Parser.parseInfix("( 6 / 2 ) - 1").eval());
        assertEquals(49.0, Parser.parseInfix("7 ^ 2").eval());


    }

}