/*package chapter9;

//import my.util.DNode;

public class DLinkedListStack<T> implements Stack<T> {

    private final DLinkedList<T> list;

    public DLinkedListStack() {
        list = new DLinkedList<>();
    }

    @Override
    public void push(T item) {
        // Done: (use tail as the top)
        list.add(item);
    }

    @Override
    public T pop() throws Exception {
        // DONE
        //will add an if statement
        //because you cant pop an empty stack
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return list.removeLast().getData();//method chaining
    }
*/
package chapter9;

import my.util.DLinkedList;
import java.util.EmptyStackException;

public class DLinkedListStack<T> implements Stack<T> {

    private final DLinkedList<T> list;

    public DLinkedListStack() {
        list = new DLinkedList<>();
    }

    @Override
    public void push(T item) {
        // TODO (use tail as the top)
        list.add(item);
    }

    @Override
    public T pop() throws Exception {
        // TODO
       
        return list.removeLast().getData();
    }

    @Override
    public T top() throws Exception {
        // TODO
        return list.get(list.size()-1).getData();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
    @Override
    public T top() throws Exception {
        // DONE
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return list.get(list.size()-1).getData();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}