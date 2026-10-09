import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parsePostFix(){
//        assertNotEquals(null, Parser.parsePostFix("4 5 /"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("4 -"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix(""));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("5 7 7 *"));
        assertThrows(IllegalArgumentException.class, ()-> Parser.parsePostFix("5 a *"));
        assertNotEquals(null, Parser.parsePostFix("3 4 2 1 - * +"));
        IO.println(Parser.parsePostFix("3 4 2 1 - * +"));
//        assertNotEquals(null, Parser.parsePostFix("5 1 2 + 4 * + 3 -"));
    }


}