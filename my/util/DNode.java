package my.util;

public class DNode<T> {

    private T data;
    private DNode<T>prev;
    private DNode<T>next;
    public DNode(DNode<T> prev, DNode<T> next, T data){
        this.prev = prev;
        this.next = next;
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public DNode<T> getPrev() {
        return prev;
    }
    public DNode<T> getNext() {
        return next;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setPrev(DNode<T> prev) {
        this.prev = prev;
    }
    public void setNext(DNode<T> next) {
        this.next = next;
    }

    

}
