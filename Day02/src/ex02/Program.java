package ex02;

import java.nio.file.Path;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !isValidInputArgument(args[0])) {
            System.exit(-1);
        }
        Path startAbsolutePath =  createStartAbsolutePath(args[0]);
        FileManager fileManager = new FileManager(startAbsolutePath);
        fileManager.run();
    }

    static private boolean isValidInputArgument(String argument) {
        String[] splitArguments = argument.split("=");
        Path startPath = Path.of(splitArguments[Math.max(0, splitArguments.length - 1)]);
        if (!startPath.isAbsolute()) {
            System.err.println("The path should be absolute!");
            return false;
        }
        return true;
    }

    static private Path createStartAbsolutePath(String argument) {
        return Path.of(argument.split("=")[1]);
    }
}