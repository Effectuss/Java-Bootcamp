package ex05;

import java.util.Objects;
import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    public void addUser(final User user) {
        usersList.addUser(user);
    }

    public Integer getBalanceUserById(final Integer id) {
        return usersList.getUserByID(id).getBalance();
    }

    public User getUserByID(final Integer id) {
        return usersList.getUserByID(id);
    }

    public Integer getBalanceUser(final User user) {
        for (int i = 0; i < usersList.getNumberOfUsers(); ++i) {
            if (usersList.getUserByIndex(i).getIdentifier().equals(user.getIdentifier())) {
                return usersList.getUserByIndex(i).getBalance();
            }
        }
        throw new UserNotFoundException("User does not exist!");
    }

    public void performTransferTransaction(final Integer idSender, final Integer idRecipient, final Integer transferAmount) {
        if (Objects.equals(idSender, idRecipient)) {
            throw new IllegalUserBalanceException("Error, sender and recipient cant be equal");
        }

        User sender = usersList.getUserByID(idSender);
        User recipient = usersList.getUserByID(idRecipient);

        Transaction credit = new Transaction(sender, recipient, Transaction.TransferCategory.CREDITS, -transferAmount);
        Transaction debit = new Transaction(sender, recipient, Transaction.TransferCategory.DEBITS, transferAmount);

        debit.setIdentifier(credit.getIdentifier());

        this.addTransactionForUsers(sender, recipient, debit, credit);
        this.changeBalanceUsers(sender, recipient, transferAmount);
    }

    private void addTransactionForUsers(final User sender, final User recipient, final Transaction debit, final Transaction credit) {
        sender.getTransactionsList().addTransaction(credit);
        recipient.getTransactionsList().addTransaction(debit);

    }

    private void changeBalanceUsers(final User sender, final User recipient, final Integer transferAmount) {
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }

    public Transaction[] getTransactionsByUser(final Integer id) {
        return usersList.getUserByID(id).getTransactionsList().toArray();
    }

    public void removeTransaction(final UUID transactionId, final Integer userId) {
        usersList.getUserByID(userId).getTransactionsList().deleteTransaction(transactionId);
    }

    public Transaction[] getUnpairTransactions() {
        Transaction[] allTransactions = getALlTransactions();
        TransactionsList result = new TransactionsLinkedList();

        for (int i = 0; i < allTransactions.length; i++) {
            int countTransaction = 0;
            for (int j = 0; j < allTransactions.length; ++j) {
                if (allTransactions[i].getIdentifier().equals(allTransactions[j].getIdentifier())) {
                    countTransaction++;
                }
            }
            if (countTransaction != 2) {
                result.addTransaction(allTransactions[i]);
            }
        }
        return result.toArray();
    }

    public Transaction[] getALlTransactions() {
        TransactionsLinkedList allTransactions = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getNumberOfUsers(); ++i) {
            Transaction[] arrTransactionsByUser = usersList.getUserByIndex(i).getTransactionsList().toArray();
            for (int j = 0; j < usersList.getUserByIndex(i).getTransactionsList().getSize(); ++j) {
                allTransactions.addTransaction(arrTransactionsByUser[j]);
            }
        }
        return allTransactions.toArray();
    }
}
