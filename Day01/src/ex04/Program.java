package ex04;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Jane", 100);
        User user2 = new User("Mike", 300);
        User user3 = new User("Sava", 800);
        User user4 = new User("Masha", 500);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        transactionsService.addUser(user3);
        transactionsService.addUser(user4);

        System.out.println("Start balances of users");
        System.out.println(transactionsService.getBalanceUser(user1));
        System.out.println(transactionsService.getBalanceUser(user2));
        System.out.println(transactionsService.getBalanceUser(user3));
        System.out.println(transactionsService.getBalanceUser(user4));

        transactionsService.performTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 100);
        transactionsService.performTransferTransaction(user2.getIdentifier(), user3.getIdentifier(), 200);

        Transaction[] allTransactions = transactionsService.getALlTransactions();

        Transaction[] user2Transactions = transactionsService.getTransactionsByUser(user2.getIdentifier());

        System.out.println("\nTransactions of user 2");
        for (Transaction transaction : user2Transactions) {
            System.out.println(transaction);
        }

        transactionsService.removeTransaction(user2Transactions[0].getIdentifier(), user2.getIdentifier());

        allTransactions = transactionsService.getALlTransactions();

        System.out.println("\nAll transactions after delete");
        for (Transaction transaction : allTransactions) {
            System.out.println(transaction);
        }

        System.out.println("\nUnpair transactions");
        Transaction[] arrUnpairTransactions = transactionsService.getUnpairTransactions();

        for (Transaction transaction : arrUnpairTransactions) {
            System.out.println(transaction);
        }

        System.out.println("\nException");
        try {
            transactionsService.performTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 10000);
        } catch (IllegalTransactionException e) {
            System.out.println(e.getMessage());
        }

        try {
            transactionsService.addUser(user1);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        try {
            User userException = new User("Vasilii", -100);
        } catch (IllegalUserBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
