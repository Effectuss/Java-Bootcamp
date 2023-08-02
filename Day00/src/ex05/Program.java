package ex05;

import java.util.Scanner;

public class Program {
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_LENGTH_NAME = 10;
    private static final int FIRST_LESSON = 1;
    private static final int LAS_LESSON = 6;
    private static final String[] nameStudents = new String[MAX_STUDENTS];

    public static void main(String[] args) {
        createSchedule();
    }

    private static void createSchedule() {
        Scanner scanner = new Scanner(System.in);
        readNameStudent(scanner);
        populateTimeTable(scanner);
        recordAttendance(scanner);
        printSchedule();
    }

    private static void recordAttendance(Scanner scanner) {

    }

    private static void populateTimeTable(Scanner scanner) {
        char[] arr = scanner.nextLine().toCharArray();

    }

    private static void readNameStudent(Scanner scanner) {
        for (int i = 0; i < MAX_STUDENTS; ++i) {
            String name = scanner.next();
            if (name.equals(".")) {
                break;
            } else if (name.length() > MAX_LENGTH_NAME) {
                exitFromProgram("Error length name");
            }
            nameStudents[i] = name;
        }
    }

    private static void printSchedule() {

    }

    private static void exitFromProgram(String exitMessage) {
        System.err.println(exitMessage);
        System.exit(-1);
    }
}

