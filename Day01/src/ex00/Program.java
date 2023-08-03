package ex00;

public class Program {
    public static void main(String[] args) {
        User recipient = new User(1, "Bob", 100);
        User sender = new User(2, "Alice", 200);
        Transaction transaction = new Transaction(recipient, sender, Transaction.TransferCategory.CREDITS, 100);
        System.out.println(recipient.toString());
        System.out.println(sender.toString());
        System.out.println(transaction.toString());
    }

}
