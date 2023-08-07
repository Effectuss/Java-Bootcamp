package ex05;

public class MenuProductionMode extends Menu {

    private final String menu = """
            1. Add a user
            2. View user balances
            3. Perform a transfer
            4. View all transactions for a specific user
            5. Finish execution
            """;

    MenuProductionMode() {
        super();
    }

    @Override
    public void runProgram() {
        while (true) {
            try {
                printMenu();
                int menuOption = readMenuOption();
                if (menuOption == 5) {
                    break;
                }
                selectMenuOption(menuOption);
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
            System.out.println(separateLine);
        }
    }

    @Override
    protected void printMenu() {
        System.out.print(menu);
    }

    @Override
    protected int readMenuOption() {
        System.out.print("-> ");
        int menuOption = Integer.parseInt(scanner.nextLine());
        if (menuOption < 0 || menuOption > 5) {
            throw new IllegalArgumentException("Invalid menu option");
        }
        return menuOption;
    }

    @Override
    protected void selectMenuOption(final int menuOption) {
        switch (menuOption) {
            case 1 -> addUser();
            case 2 -> showUserBalance();
            case 3 -> performTransferTransaction();
            case 4 -> showAllTransactionsByUser();
        }
    }
}
