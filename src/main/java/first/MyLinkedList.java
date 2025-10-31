package first;

import java.util.Collection;

public class MyLinkedList<T> {
    private Node head = null;
    private int length = 0;

    private class Node {
        private final T value;
        private Node next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.format("value: %s, next Node: %s", this.value, this.next);
        }
    }

    public int getLength() {
        return length;
    }

    private Node getNode(int index) {
        Node currNode = head;
        if (index >= length) return null;
        else {
            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }
        }
        return currNode;
    }

    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        length += 1;
    }

    public T get(int index) {
        if (index >= length) return null;
        if (index == 0) return head.value;
        Node currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.value;
    }

    public T remove(int index) {
        if (index >= length) return null;
        T result;
        if (index == 0) {
            result = head.value;
            head = head.next;
        } else {
            Node currNode = head;
            Node prevNode = null;
            for (int i = 0; i < index; i++) {
                prevNode = currNode;
                currNode = currNode.next;
            }
            result = currNode.value;
            prevNode.next = currNode.next;
        }
        length -= 1;
        return result;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (index >= length || c.isEmpty()) return false;

        Node currNode = this.getNode(index);
        Node tailNode = currNode.next;
        for (T elem : c) {
            currNode.next = new Node(elem);
            currNode = currNode.next;
            length += 1;
        }
        currNode.next = tailNode;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) return false;
        for (T elem : c) {
            this.add(elem);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (length == 0) str.append("empty");
        for (int i = 0; i < length; i++) {
            str.append(this.getNode(i).toString()).append("\n");
        }
        return str.toString();
    }
}