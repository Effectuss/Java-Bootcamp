package ex04;

import java.util.Objects;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public Integer getBalanceUserById(Integer id) {
        return usersList.getUserByID(id).getBalance();
    }

    public Integer getBalanceUser(User user) {
        for (int i = 0; i < usersList.getNumberOfUsers(); ++i) {
            if (usersList.getUserByIndex(i).getIdentifier().equals(user.getIdentifier())) {
                return usersList.getUserByIndex(i).getBalance();
            }
        }
        throw new UserNotFoundException("User does not exist!");
    }

    public void performTransferTransaction(Integer idSender, Integer idRecipient, Integer transferAmount) {
        if (Objects.equals(idSender, idRecipient)) {
            throw new IllegalUserBalanceException("Error, sender and recipient cant be equal");
        }

        User sender = usersList.getUserByID(idSender);
        User recipient = usersList.getUserByID(idRecipient);

        Transaction credit = new Transaction(sender, recipient, Transaction.TransferCategory.CREDITS, -transferAmount);
        Transaction debit = new Transaction(sender, recipient, Transaction.TransferCategory.DEBITS, transferAmount);

        debit.setIdentifier(credit.getIdentifier());

        sender.getTransactionsList().addTransaction(credit);
        recipient.getTransactionsList().addTransaction(debit);

        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(sender.getBalance() + transferAmount);
    }

    public Transaction[] getTransactionsByUser(Integer id) {
            return usersList.getUserByID(id).getTransactionsList().toArray();
    }

    public void removeTransaction(UUID transactionId, Integer userId) {
        usersList.getUserByID(userId).getTransactionsList().deleteTransaction(transactionId);
    }

    public Transaction[] getUnpairTransactions() {

    }
}
