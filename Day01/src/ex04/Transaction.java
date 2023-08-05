package ex04;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBITS,
        CREDITS
    }

    private UUID identifier;
    private final User sender;
    private final User recipient;
    private TransferCategory transferCategory;
    private Integer transferAmount;
    private Transaction next;

    public Transaction(User sender, User recipient, TransferCategory transferCategory, Integer transferAmount) {
        this.identifier = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setTransferCategory(TransferCategory transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setNextTransaction(Transaction next) {
        this.next = next;
    }

    public Transaction getNextTransaction() {
        return next;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        if (transferCategory == TransferCategory.CREDITS && (transferAmount > 0 || sender.getBalance() < -transferAmount)) {
            throw new IllegalTransactionException("The transfer of the amount exceeding users balance");
        } else if (transferCategory == TransferCategory.DEBITS && (transferAmount < 0 || sender.getBalance() < transferAmount)) {
            throw new IllegalTransactionException("The transfer of the amount exceeding users balance");
        }
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier=" + identifier +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
