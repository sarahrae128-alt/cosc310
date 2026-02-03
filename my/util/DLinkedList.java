package my.util;

public class DLinkedList<T> {

    private DNode<T> head;
    private DNode<T> tail;
    private int size;

    public DLinkedList(){

    }
    
    public void addAfter(DNode<T> node, T data){
        size++;
    }
    public void add(T data){
        size++;
    }

}
