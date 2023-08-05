package ex03;

import ex04.TransactionsList;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBITS,
        CREDITS
    }

    private final UUID identifier;
    private final User sender;
    private final User recipient;
    private final TransferCategory transferCategory;
    private Integer transferAmount;
    private Transaction next;

    public Transaction(User sender, User recipient, TransferCategory transferCategory, Integer transferAmount) {
        this.identifier = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.transferCategory = transferCategory;
        setTransferAmount(transferAmount);
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
            throw new TransactionException("Error, incorrect sign or transfer amount");
        } else if (transferCategory == TransferCategory.DEBITS && (transferAmount < 0 || sender.getBalance() < transferAmount)) {
            throw new TransactionException("Error, incorrect sign or transfer amount");
        }
        this.transferAmount = transferAmount;
        changeBalanceUsers();
    }

    private void changeBalanceUsers() {
        if (transferCategory == TransferCategory.CREDITS) {
            sender.setBalance(sender.getBalance() + transferAmount);
            recipient.setBalance(recipient.getBalance() - transferAmount);
        } else if (transferCategory == TransferCategory.DEBITS) {
            sender.setBalance(sender.getBalance() + transferAmount);
            recipient.setBalance(recipient.getBalance() - transferAmount);
        }
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
