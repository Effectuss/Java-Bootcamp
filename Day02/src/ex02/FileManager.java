package ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.spec.RSAOtherPrimeInfo;
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
        try (var scanner = new Scanner(System.in)) {
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
        String[] commands = command.split(" ");
        if (!isValidEntryCommand(commands)) {
            System.out.println("Invalid command!");
            return;
        }
        String commandName = commands[0];
        switch (commandName) {
            case "mv":
                executeCommandMV(currentAbsolutePath.resolve(commands[1]), Path.of(commands[2]));
                break;
            case "cd":
                if (commands.length == 2) {
                    executeCommandCD(currentAbsolutePath.resolve(commands[1]));
                } else {
                    executeCommandCD(Path.of(commands[0]));
                }
                break;
            case "ls":
                executeCommandLS();
                break;
            default:
                System.out.println("Unknown command!");
                break;
        }
    }

    private void executeCommandLS() {
        try (Stream<Path> stream = Files.list(currentAbsolutePath)) {
            stream.forEach(this::printFileInfo);
        } catch (IOException e) {
            System.out.println("Error listing files: " + e.getMessage());
        }
    }

    private void printFileInfo(Path file) {
        try {
            if (Files.isDirectory(file)) {
                long dirSize = calculateDirectorySize(file);
                System.out.println(file.getFileName() + " " + (double) dirSize / 1024 + " KB");
            } else {
                long fileSize = Files.size(file);
                System.out.println(file.getFileName() + " " + (double) fileSize / 1024 + " KB");
            }
        } catch (IOException e) {
            System.err.println("Error getting file size: " + e.getMessage());
        }
    }

    private long calculateDirectorySize(Path directory) throws IOException {
        try (Stream<Path> stream = Files.list(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .mapToLong(file -> {
                        try {
                            return Files.size(file);
                        } catch (IOException e) {
                            System.err.println("Error getting size of file: " + file);
                            return 0;
                        }
                    })
                    .sum();
        }
    }

    private void executeCommandCD(Path pathForMove) {
        if (pathForMove.toString().equals("cd")) {
            goToHomeDirectory();
        } else if (Files.exists(pathForMove)) {
            currentAbsolutePath = pathForMove.toAbsolutePath().normalize();
            System.out.println(currentAbsolutePath);
        } else {
            System.out.println("No such file or directory: " + pathForMove);
        }
    }

    private void goToHomeDirectory() {
        String homeDirectory = System.getProperty("user.home");
        currentAbsolutePath = Path.of(homeDirectory).toAbsolutePath();
        System.out.println(currentAbsolutePath);
    }

    private void executeCommandMV(Path sourceFile, Path targetFile) {
        if (targetFile.toString().contains("/")) {
            renameFile(sourceFile, targetFile);
        } else {
            moveFile(sourceFile, targetFile);
        }
    }

    private void renameFile(Path sourceFile, Path targetFile) {
        try {
            targetFile = currentAbsolutePath.resolve(targetFile).resolve(sourceFile.getFileName());
            Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Rename " + sourceFile + " to " + targetFile
                    + ": No such file or directory");
        }
    }

    private void moveFile(Path sourceFile, Path targetFile) {
        try {
            targetFile = currentAbsolutePath.resolve(targetFile.toString());
            Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Move " + sourceFile + " to " + targetFile
                    + ": No such file or directory");
        }
    }

    private boolean isValidEntryCommand(String[] commands) {
        int length = commands.length;

        return switch (commands[0]) {
            case "ls" -> length == 1;
            case "cd" -> length == 2 || length == 1;
            case "mv" -> length == 3;
            default -> false;
        };
    }
}
