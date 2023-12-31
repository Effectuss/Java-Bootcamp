package ex05.main;

import ex05.Menu;
import ex05.MenuDevMode;
import ex05.MenuProductionMode;

public class Program {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--profile=dev")) {
            Menu menu = new MenuDevMode();
            menu.runProgram();
        } else if (args.length == 1 && args[0].equals("--profile=production")) {
            Menu menu = new MenuProductionMode();
            menu.runProgram();
        } else {
            System.out.println("""
                    Please, launch program with modes:
                    --profile=dev for dev mode
                    --profile=production for production mode""");
        }
    }
}
