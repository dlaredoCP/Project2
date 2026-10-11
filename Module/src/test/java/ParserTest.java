import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parsePostFix(){
        assertNotEquals(null, Parser.parsePostFix("4 5 /"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("4 -"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("  "));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("5 7 7 *"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("5 a *"));
        assertNotEquals(null, Parser.parsePostFix("3 4 - 2 1 * +"));
    }

    @Test
    void parseInfix(){
        assertNotEquals(null, Parser.parseInfix("( 5 + 13 ) * 12"));
        assertNotEquals(null, Parser.parseInfix("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));
        assertNotEquals(null, Parser.parseInfix("( 3 ^ 4 ) * ( 1 - 4 )"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parseInfix("( a 5 + 13 ) * 12"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parseInfix("5 + 13 * ( 12"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parseInfix("5 + 13 ) * 12"));
    }

    @Test
    void Precedence(){
        assertEquals(2, Parser.precedence("/"));
    }

    @Test
    void leftAssoc(){
        assertTrue(Parser.leftAssoc("*"));
    }


}