import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void empty_stack(){
        assertNotEquals(null, ArrayStack.empty_stack());
    }

    @Test
    void push(){
        ArrayStack<Integer> ex = ArrayStack.empty_stack();
        ex.push(2);
        ex.push(15);
        assertNotEquals(ArrayStack.empty_stack(), ex);
    }

    @Test
    void pop(){
        ArrayStack<Integer> ex = ArrayStack.empty_stack();
        ArrayStack<Integer> ex2 = ArrayStack.empty_stack();
        ex.push(2);
        assertEquals(2,ex.pop());
        assertThrows(IndexOutOfBoundsException.class, ()-> ex2.pop());
    }

    @Test
    void peek(){
        ArrayStack<Integer> ex = ArrayStack.empty_stack();
        ArrayStack<Integer> ex2 = ArrayStack.empty_stack();
        ex.push(2);
        ex.push(15);
        assertEquals(15, ex.peek());
        assertThrows(IndexOutOfBoundsException.class, ()-> ex2.peek());

    }

    @Test
    void isEmpty(){
        ArrayStack<String> ex = ArrayStack.empty_stack();
        ex.push("3");
        ex.push("abc");
        assertEquals(false, ex.isEmpty());
    }

    @Test
    void size(){
        ArrayStack<String> ex = ArrayStack.empty_stack();
        ex.push("3");
        ex.push("abc");
        assertEquals(2, ex.size());
    }
}