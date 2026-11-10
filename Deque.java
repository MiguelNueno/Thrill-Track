/**
 * A Deque (double-ended queue) implementation using a doubly linked chain.
 *
 * @param <T> the type of elements held in this deque
 * 
 * @author davidsage
 */


public class Deque<T> {
    private static class DLNode<T> {
        private T data;
        private DLNode<T> next;
        private DLNode<T> prev;

        public DLNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public T getData() {
            return data;
        }

        public DLNode<T> getNext() {
            return next;
        }

        public void setNext(DLNode<T> next) {
            this.next = next;
        }

        public DLNode<T> getPrev() {
            return prev;
        }

        public void setPrev(DLNode<T> prev) {
            this.prev = prev;
        }


    }

    private DLNode<T> head;
    private DLNode<T> tail;
    private int size;


    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addToBack(T data) {
        DLNode<T> newNode = new DLNode<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    public void addToFront(T data) {
        DLNode<T> newNode = new DLNode<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void add(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            DLNode<T> newNode = new DLNode<>(data);
            DLNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            newNode.setPrev(current.getPrev());
            newNode.setNext(current);
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
            size++;
        }
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
        return data;
    }

    public T remove(T data) {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        DLNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                T dataToRemove = null;
                if (current == head) {
                    return removeFromFront();
                } else if (current == tail) {
                    dataToRemove = tail.getData();
                    tail = tail.getPrev();
                    tail.setNext(null);
                    size--;
                } else {
                    dataToRemove = current.getData();
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                    size--;
                }
                return dataToRemove;
            }
            current = current.getNext();
        }
        throw new IllegalArgumentException("Element not found in deque");
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
        return data;
    }

    public boolean contains(T data) {
        DLNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


}