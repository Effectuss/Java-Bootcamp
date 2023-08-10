package ex00;

public class Program {
    public static void main(String[] args) {
        if (args.length == 0 || !isValidProgramArguments(args)) {
            System.err.println("Invalid program arguments");
            System.exit(-1);
        }
        try {
            int count = getCountHumans(args);
            Thread eggRunnable = new Thread(new EggPrinter(count));
            Thread henRunnable = new Thread(new HenPrinter(count));

            eggRunnable.start();
            henRunnable.start();
            eggRunnable.join();
            henRunnable.join();
            printHuman(count);

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

    public static void printHuman(int countHumans) {
        for (int i = 0; i < countHumans; ++i) {
            System.out.println("Human");
        }
    }

    public static boolean isValidProgramArguments(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Arguments must be one");
            }
            String[] checkArgs = args[0].split("=");
            if (checkArgs.length != 2) {
                throw new IllegalArgumentException("Invalid arguments");
            }
            int tmpNumber = Integer.parseInt(checkArgs[1]);
            if (tmpNumber <= 0) {
                throw new IllegalArgumentException("The number of count must be a positive integer");
            }
            return checkArgs[0].equals("--count") && checkArgs[1].equals(String.valueOf(tmpNumber));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid arguments: " + e.getMessage());
            return false;
        }
    }

    public static int getCountHumans(String[] args) {
        return Integer.parseInt(args[0].split("=")[1]);
    }
}
