package ex05;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);

    public void deleteTransaction(UUID id);

    public Transaction[] toArray();

    public Integer getSize();

    public Transaction getTransactionById(UUID id);
}
