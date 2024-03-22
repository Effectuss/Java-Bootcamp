package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node head;
    private Node tail;
    private Integer size;

    private static class Node {
        Node(Transaction transaction) {
            this.transaction = transaction;
            next = null;
            prev = null;
        }

        public Node next;
        public Node prev;
        public Transaction transaction;
    }

    public TransactionsLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        ++size;
    }

    @Override
    public void deleteTransaction(UUID id) {
        Node current = head;
        while (current != null) {
            if (current.transaction.getIdentifier().equals(id)) {
                if (current == head) {
                    deleteHeadElement(current);
                } else if (current == tail) {
                    deleteTailElement(current);
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                --size;
                return;
            }
            current = current.next;
        }
        throw new TransactionNotFoundException("Transaction with " + id + " not found!");
    }

    private void deleteHeadElement(Node current) {
        head = current.next;
        if (head != null) {
            head.prev = null;
        }
        if (current == tail) {
            tail = null;
        }
    }

    private void deleteTailElement(Node current) {
        tail = current.prev;
        tail.next = null;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.transaction;
            current = current.next;
            ++index;
        }
        return array;
    }
}
