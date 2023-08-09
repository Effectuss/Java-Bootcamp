package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileManager {

    private Path currentAbsolutePath;

    public FileManager(Path startAbsolutePath) {
        if (!startAbsolutePath.isAbsolute()) {
            throw new IllegalArgumentException("The path should be absolute!");
        }
        this.currentAbsolutePath = startAbsolutePath;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(currentAbsolutePath.toString());
            System.out.print("-> ");
            String inputCommand = scanner.nextLine();
            while (!inputCommand.equals("exit")) {
                executeCommands(inputCommand);
                System.out.print("-> ");
                inputCommand = scanner.nextLine();
            }
        }
    }

    private void executeCommands(String command) {
        if (!isValidEntryCommand(command)) {
            System.out.println("Invalid command!");
            return;
        }
        switch (command.split(" ")[0]) {
            case "mv" -> executeCommandMV(
                    currentAbsolutePath.resolve((command.split(" ")[1])),
                    currentAbsolutePath.resolve(command.split(" ")[2]));
            case "cd" -> executeCommandCD(currentAbsolutePath.resolve(command.split(" ")[1]));
            case "ls" -> executeCommandLS();
            default -> System.out.println("Unknown command!");
        }
    }

    private void executeCommandLS() {
        try (Stream<Path> stream = Files.list(currentAbsolutePath)) {
            stream.forEach(file -> {
                try {
                    long fileSize = Files.size(file);

                    System.out.println(file.getFileName() + " " + (double) fileSize / 1024 + " KB");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executeCommandCD(Path pathForMove) {
        if (Files.exists(pathForMove)) {
            currentAbsolutePath = pathForMove.toAbsolutePath().normalize();
            System.out.println(currentAbsolutePath);
        } else {
            System.out.println("No such file or directory: " + pathForMove);
        }
    }

    private void executeCommandMV(Path sourceFile, Path targetFile) {
        try {
            System.out.println(targetFile.toAbsolutePath().normalize());
            String s = targetFile.toAbsolutePath().normalize().toString() + "/" +sourceFile.getFileName();
            System.out.println(s);
            Files.move(sourceFile, Path.of(s), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Rename " + sourceFile + " to " + targetFile
                    + ": No such file or directory");
        }
    }

    private boolean isValidEntryCommand(String command) {
        String[] commands = command.split(" ");
        int length = commands.length;

        return switch (commands[0]) {
            case "ls" -> length == 1;
            case "cd" -> length == 2;
            case "mv" -> length == 3;
            default -> false;
        };
    }
}
