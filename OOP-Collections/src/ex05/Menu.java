package ex05;

import java.util.Scanner;
import java.util.UUID;

public abstract class Menu {
    protected Scanner scanner;
    protected TransactionsService facade;
    protected final String separateLine = "------------------------------------------------";
    private final int EXIT;

    public Menu(int exitNumber) {
        scanner = new Scanner(System.in);
        facade = new TransactionsService();
        EXIT = exitNumber;
    }

    public void runProgram() {
        while (true) {
            try {
                printMenu();
                int menuOption = readMenuOption();
                if (menuOption == EXIT) {
                    System.out.println("Good bye! See you later ;)");
                    break;
                }
                selectMenuOption(menuOption);
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
            System.out.println(separateLine);
        }
    }

    protected abstract int readMenuOption();

    protected abstract void validateMenuOption(int menuOption);

    protected abstract void printMenu();

    protected abstract void selectMenuOption(final int menuOption);

    protected void addUser() {
        System.out.println("Enter a user name and a balance");
        String userData = scanner.nextLine().trim();
        String[] userDataArray = userData.split(" ");
        if (userDataArray.length != 2) {
            throw new RuntimeException("Please, enter a user name and a balance");
        }
        String userName = userDataArray[0];
        Integer userBalance = Integer.parseInt(userDataArray[1]);
        User tmpUser = new User(userName, userBalance);
        facade.addUser(tmpUser);
        System.out.println("User with id = " + tmpUser.getIdentifier() + " is added");
    }

    protected void showUserBalance() {
        System.out.println("Enter a user ID");
        Integer userId = Integer.parseInt(scanner.nextLine());
        User tmpUser = facade.getUserByID(userId);
        System.out.println(tmpUser.getName() + " - " + tmpUser.getBalance());
    }

    protected void performTransferTransaction() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String input = scanner.nextLine().trim();
        String[] inputArray = input.split(" ");
        if (inputArray.length != 3) {
            throw new IllegalArgumentException("Please, enter a sender ID, a recipient ID, and a transfer amount");
        }
        Integer senderId = Integer.parseInt(inputArray[0]);
        Integer recipientId = Integer.parseInt(inputArray[1]);
        Integer transferAmount = Integer.parseInt(inputArray[2]);
        facade.performTransferTransaction(senderId, recipientId, transferAmount);
        System.out.println("The transfer is completed");
    }

    protected void showAllTransactionsByUser() {
        System.out.println("Enter a user ID");
        Integer userId = Integer.parseInt(scanner.nextLine());
        Transaction[] transactionsUser = facade.getTransactionsByUser(userId);
        for (Transaction transaction : transactionsUser) {
            printFullDataOfTransaction(transaction);
        }
    }

    protected void printFullDataOfTransaction(Transaction transaction) {
        if (transaction.getTransferCategory() == Transaction.TransferCategory.CREDITS) {
            printCreditTransaction(transaction);
        } else if (transaction.getTransferCategory() == Transaction.TransferCategory.DEBITS) {
            printDebitTransaction(transaction);
        }
    }

    protected void printCreditTransaction(Transaction transaction) {
        System.out.println("To " + transaction.getRecipient().getName() + "(id = " +
                transaction.getRecipient().getIdentifier() + ") " +
                transaction.getTransferAmount() + " with id = " + transaction.getIdentifier());
    }

    protected void printDebitTransaction(Transaction transaction) {
        System.out.println("From " + transaction.getSender().getName() + "(id = " +
                transaction.getSender().getIdentifier() + ") "
                + transaction.getTransferAmount() + " with id = " + transaction.getIdentifier());
    }

}
