package ex05;

import java.util.UUID;

public interface Menu {
    public void RunProgram();

    public void addUser(final String name, final Integer balance);

    public void showUserBalance(final Integer userId);

    public void performTransferTransaction(final UUID senderId, final UUID recipientId, final Integer amount);

    public void showAllTransactionsByUser(final   Integer userId);

}
