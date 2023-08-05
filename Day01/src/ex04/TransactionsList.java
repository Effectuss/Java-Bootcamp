package ex04;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);

    public void deleteTransaction(UUID id);

    public Transaction[] toArray();


}
