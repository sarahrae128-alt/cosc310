package chapter9;

import java.util.ArrayList;

public class SortedArrayListPriorityQueue<T> implements PriorityQueue<T> {

<<<<<<< HEAD
    private static class Entry<T> {
=======
    private static class Entry<T> implements Comparable<Entry<T>> {
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
        final int priority;
        final T data;
        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }
<<<<<<< HEAD
=======

        @Override
        public int compareTo(Entry<T> o) {
            return this.priority - o.priority;
        }
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
    }

    private final ArrayList<Entry<T>> list;

    public SortedArrayListPriorityQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void enqueue(int priority, T data) {
        // TODO: insert so list is sorted by priority ASC (lower number is higher priority)
<<<<<<< HEAD
=======
        list.add(new Entry<>(priority, data));
        list.sort(null);
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
    }

    @Override
    public T dequeue() throws Exception {
        // TODO: remove index 0
<<<<<<< HEAD
        return null;
=======
        return list.remove(0).data;
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
    }

    @Override
    public T front() throws Exception {
        // TODO: return index 0
<<<<<<< HEAD
        return null;
=======
        return list.getFirst().data;
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 62bce540d61c72a62eee71ec5807b394dfcfb8e4
