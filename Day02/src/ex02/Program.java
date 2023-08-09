package ex02;

import java.nio.file.Path;

public class Program {
    public static void main(String[] args) {
        if (!isValidInputArgument(args)) {
            System.exit(-1);
        }

    }

    static private boolean isValidInputArgument(String[] arguments) {
        if (arguments.length != 1) {
            System.out.println("Incorrect number of arguments!");
            return false;
        }
        Path startPath = Path.of(arguments[0].split("=")[Math.max(0, arguments[0].split("=").length - 1)]);
        if (!startPath.isAbsolute()) {
            System.out.println("The path should be absolute!");
            return false;
        }
        return true;
    }
}
