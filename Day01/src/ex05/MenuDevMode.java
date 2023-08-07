package ex05;

import java.util.UUID;

public class MenuDevMode extends Menu {

    private final String menu = """
            1. Add a user
            2. View user balances
            3. Perform a transfer
            4. View all transactions for a specific user
            5. DEV - remove a transfer by ID
            6. DEV - check transfer validity
            7. Finish execution
            """;
    private final int EXIT = 7;

    public MenuDevMode() {
        super(7);
    }

    @Override
    protected void printMenu() {
        System.out.print(menu);
    }

    @Override
    protected int readMenuOption() {
        System.out.print("-> ");
        int menuOption = Integer.parseInt(scanner.nextLine());
        validateMenuOption(menuOption);
        return menuOption;
    }

    @Override
    protected void validateMenuOption(int menuOption) {
        if (menuOption < 0 || menuOption > EXIT) {
            throw new IllegalArgumentException("Invalid menu option");
        }
    }

    @Override
    protected void selectMenuOption(final int menuOption) {
        switch (menuOption) {
            case 1 -> addUser();
            case 2 -> showUserBalance();
            case 3 -> performTransferTransaction();
            case 4 -> showAllTransactionsByUser();
            case 5 -> removeTransferById();
            case 6 -> checkTransferValidity();
        }
    }

    private void removeTransferById() {
        System.out.println("Enter a user ID and a transfer ID");
        String input = scanner.nextLine().trim();
        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) {
            throw new IllegalArgumentException("Please, enter a user ID and a transfer ID");
        }
        Integer userId = Integer.parseInt(inputArray[0]);
        UUID transferId = UUID.fromString(inputArray[1]);
        Transaction tmpTransaction = facade.getUserByID(userId).getTransactionsList().getTransactionById(transferId);
        facade.removeTransaction(transferId, userId);
        printTransactionInfoAfterDelete(tmpTransaction);

    }

    private void checkTransferValidity() {
        System.out.println("Check results:");
        printUnacknowledgedTransactions();
    }

    private void printUnacknowledgedTransactions() {
        for (Transaction transaction : facade.getUnpairTransactions()) {
            if (transaction.getTransferCategory() == Transaction.TransferCategory.DEBITS) {
                printUnacknowledgedDebits(transaction);
            } else if (transaction.getTransferCategory() == Transaction.TransferCategory.CREDITS) {
                printUnacknowledgedCredits(transaction);
            }
        }
    }

    private void printTransactionInfoAfterDelete(Transaction transaction) {
        if (transaction.getTransferCategory() == Transaction.TransferCategory.CREDITS) {
            System.out.println("Transfer " + "To " + transaction.getRecipient().getName() +
                    "(id = " + transaction.getRecipient().getIdentifier() + ") " +
                    -transaction.getTransferAmount() + " removed");
        } else if (transaction.getTransferCategory() == Transaction.TransferCategory.DEBITS) {
            System.out.println("Transfer " + "From " + transaction.getSender().getName() +
                    "(id = " + transaction.getSender().getIdentifier() + ") " +
                    transaction.getTransferAmount() + " removed");
        }
    }

    private void printUnacknowledgedDebits(Transaction transaction) {
        System.out.println(transaction.getRecipient().getName() + "(id = " +
                transaction.getRecipient().getIdentifier() + ") has an unacknowledged transfer id = " +
                transaction.getIdentifier() + " from " + transaction.getSender().getName() + "(id = " +
                transaction.getSender().getIdentifier() + ") for " + transaction.getTransferAmount());
    }

    private void printUnacknowledgedCredits(Transaction transaction) {
        System.out.println(transaction.getSender().getName() + "(id = " +
                transaction.getSender().getIdentifier() + ") has an unacknowledged transfer id = " +
                transaction.getIdentifier() + " to " + transaction.getRecipient().getName() + "(id = " +
                transaction.getRecipient().getIdentifier() + ") for " + -transaction.getTransferAmount());
    }
}
