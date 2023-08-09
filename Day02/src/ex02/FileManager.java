package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
            case "mv" -> executeMV();
            case "cd" -> executeCD(currentAbsolutePath.resolve(command.split(" ")[1]));
            case "ls" -> executeLS();
            default -> System.out.println("Unknown command!");
        }
    }

    private void executeLS() {
        try (Stream<Path> stream = Files.list(currentAbsolutePath)) {
            stream.forEach(file -> {
                try {
                    long fileSize = Files.size(file);

                    System.out.println(file.getFileName() + " " + (double) fileSize / 1024 + " KB");
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void executeCD(Path pathForMove) {
    }

    private void executeMV() {

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
