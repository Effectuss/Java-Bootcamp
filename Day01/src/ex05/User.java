package ex05;

public class User {
    private final Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsList transactionsList;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().getId();
        this.setName(name);
        this.setBalance(balance);
        this.transactionsList = new TransactionsLinkedList();
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            throw new IllegalUserBalanceException("Error, balance cant be negative");
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
