package chapter9;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {

    ArrayList<T> data = new ArrayList<>();

    /****
     * designate the end of the array list as the top of stack
     * @param d - one peice of data we want to add to the stack  
     */
    @Override
    public void push(T d) {
        data.add(d);
    }

    @Override
    public T pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return data.removeLast();
    }

    @Override
    public T top() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return data.getLast();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        if( isEmpty()){
            return "ArrayStack [data=" + data + ", top()= EMPTY, size()=" + size() + "]";
        }else{
            return "ArrayStack [data=" + data + ", top()=" + top() + ", size()=" + size() + "]";
        }
    }

    
}
