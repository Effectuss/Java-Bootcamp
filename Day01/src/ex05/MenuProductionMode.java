package ex05;

public class MenuProductionMode extends Menu {

    private final String menu = """
            1. Add a user
            2. View user balances
            3. Perform a transfer
            4. View all transactions for a specific user
            5. Finish execution
            """;
    private final int EXIT = 5;

    public MenuProductionMode() {
        super(5);
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
        }
    }
}
