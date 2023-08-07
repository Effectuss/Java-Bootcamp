package ex05;

import java.util.Scanner;
import java.util.UUID;

public abstract class Menu {
    protected Scanner scanner;
    protected TransactionsService facade;
    protected final String separateLine = "------------------------------------------------";

    Menu() {
        scanner = new Scanner(System.in);
        facade = new TransactionsService();
    }

    public abstract void runProgram();

    protected abstract int readMenuOption();

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
        facade.addUser(new User(userName, userBalance));
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
    }

    protected void showAllTransactionsByUser() {
        System.out.println("Enter a user ID");
        Integer userId = scanner.nextInt();
        Transaction[] transactionsUser = facade.getTransactionsByUser(userId);
        for (Transaction transaction : transactionsUser) {
            System.out.println(transaction);
        }
    }

}
