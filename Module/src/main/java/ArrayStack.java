public class ArrayStack<T>{

    private int count;
    private T[] arrStack;

    public ArrayStack (T[] ArrStack){
        count = 0;
        arrStack = ArrStack;
    }

    /** Takes no arguments and returns an empty stack */
    public static <T> ArrayStack<T> empty_stack() {
        return new ArrayStack<>(createArray(2));
    }

    /** Accepts an Object T and pushes it onto the stack */
    public void push(T obj){
        if (count == 0){
            arrStack[0] = obj;
            count++;
        } else {
            if (count==arrStack.length){
                T[] newArr = createArray(arrStack.length*2);
                System.arraycopy(arrStack, 0, newArr, 0, arrStack.length);
                arrStack = newArr;
            }
            arrStack[count++] = obj;
        }
    }

    /** Removes (and returns) the item at the “top” of the stack */
    public T pop(){
        if (count == 0){
            throw new IndexOutOfBoundsException("No elements to pop; push some first before popping!");
        } else {
            T obj = arrStack[count-1];
            arrStack[count--] = null;
            return obj;
        }
    }

    /** */
    public T peek(){
        if (count == 0){
            throw new IndexOutOfBoundsException("No elements to peek at; push some first before peeking!");
        } else {
            return arrStack[count-1];
        }
    }

    /** Takes no arguments and returns the number of items in the stack */
    public int size(){
        return count;
    }

    /** Takes in the desired array capacity and returns a new T[capacity] */
    @SuppressWarnings("unchecked")
    private static <T> T[] createArray(int capacity) {
        return (T[]) new Object[capacity];
    }

}
