package ex05;

import java.util.Scanner;
import java.util.UUID;

public abstract class Menu {
    protected Scanner scanner;
    protected TransactionsService facade;

    Menu() {
        scanner = new Scanner(System.in);
        facade = new TransactionsService();
        System.out.println("Menu constructor");
    }

    public abstract void runProgram();

    protected abstract void readMenuOption();

    protected void addUser() {
        System.out.println("Enter a user name and a balance");
        try {
            String userData = scanner.nextLine().trim();
            String[] userDataArray = userData.split(" ");
            if (userDataArray.length != 2) {
                throw new RuntimeException("Please, enter a user name and a balance");
            }
            String userName = userDataArray[0];
            Integer userBalance = Integer.parseInt(userDataArray[1]);
            facade.addUser(new User(userName, userBalance));
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    protected void showUserBalance() {
        System.out.println("Enter a user ID");
        try {
            Integer userId = scanner.nextInt();
            User tmpUser = facade.getUserByID(userId);
            System.out.println(tmpUser.getName() + " - " + tmpUser.getBalance());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    protected void performTransferTransaction() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        try {
            String input = scanner.nextLine().trim();
            String[] inputArray = input.split(" ");
            if (inputArray.length != 3) {
                throw new RuntimeException("Please, enter a sender ID, a recipient ID, and a transfer amount");
            }
            Integer senderId = Integer.parseInt(inputArray[0]);
            Integer recipientId = Integer.parseInt(inputArray[1]);
            Integer transferAmount = Integer.parseInt(inputArray[2]);
            facade.performTransferTransaction(senderId, recipientId, transferAmount);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    protected void showAllTransactionsByUser() {
        System.out.println("Enter a user ID");
        try {
            Integer userId = scanner.nextInt();
            Transaction[] transactionsUser = facade.getTransactionsByUser(userId);
            for (Transaction transaction : transactionsUser) {
                System.out.println(transaction);
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}
