package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "John", 600);
        User user2 = new User(2, "Mike", 100);
        Transaction transaction = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, -345);
        System.out.println(user1.getName());
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(transaction);

        User user3 = new User(3, "Vast", 300);
        User user4 = new User(6, "Peter", 400);
        Transaction transaction2 = new Transaction(user3, user4, Transaction.TransferCategory.DEBITS, 100);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(transaction2);

        // checking incorrect date
//        User user5 = new User(5, "Masha", -300);
//        Transaction transaction3 = new Transaction(user1, user2, Transaction.TransferCategory.DEBITS, -100);
//        Transaction transaction4 = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, 100);
//        Transaction transaction5 = new Transaction(user1, user2, Transaction.TransferCategory.CREDITS, 10000);
//        Transaction transaction6 = new Transaction(user1, user2, Transaction.TransferCategory.DEBITS, -10000);
    }

}
