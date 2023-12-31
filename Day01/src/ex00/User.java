package ex00;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        this.setName(name);
        this.setBalance(balance);
        this.setIdentifier(identifier);
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

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            System.err.println("Error, balance cant be negative");
            System.exit(-1);
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
