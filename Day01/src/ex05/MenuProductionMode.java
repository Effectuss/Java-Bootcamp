package ex05;

import java.util.Scanner;

public class MenuProductionMode extends Menu {

    MenuProductionMode() {
        super();
    }

    @Override
    public void runProgram() {
        addUser();
        addUser();
        performTransferTransaction();
        performTransferTransaction();
        showAllTransactionsByUser();
        showUserBalance();
    }

    @Override
    protected void readMenuOption() {

    }
}
