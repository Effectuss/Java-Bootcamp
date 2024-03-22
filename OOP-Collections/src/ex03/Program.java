package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsLinkedList list = new TransactionsLinkedList();

        User user1 = new User("John", 100);
        User user2 = new User("Jane", 200);
        User user3 = new User("Bob", 300);
        User user4 = new User("Alice", 400);

        Transaction transaction = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, -100);
        Transaction transaction1 = new Transaction(user2, user3, Transaction.TransferCategory.DEBITS, 100);
        Transaction transaction2 = new Transaction(user3, user4, Transaction.TransferCategory.CREDITS, -200);

        list.addTransaction(transaction);
        list.addTransaction(transaction1);
        list.addTransaction(transaction2);

        Transaction[] array = list.toArray();
        System.out.println(list.getSize());

        for (Transaction tran : array) {
            System.out.println(tran);
        }

        list.deleteTransaction(transaction1.getIdentifier());
        list.deleteTransaction(transaction.getIdentifier());

        array = list.toArray();
        System.out.println(list.getSize());

        for (Transaction tran : array) {
            System.out.println(tran);
        }

        try {
            list.deleteTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
