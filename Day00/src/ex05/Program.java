package ex05;

import java.util.Scanner;

public class Program {
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_LENGTH_NAME = 10;
    private static final int MAX_LESSONS_PER_WEEK = 10;
    private static final int MAX_LENGTH_TIME_TABLE = 4;
    private static final int MIN_LESSON_TIME = 1;
    private static final int MAX_LESSON_TIME = 6;
    private static final int DAYS_ON_WEEK = 7;
    private static final String[] nameStudents = new String[MAX_STUDENTS];
    private static final int[][] timeTable = new int[DAYS_ON_WEEK][MAX_LESSONS_PER_WEEK];
    private static final String[][][][] schedule = new String[10][31][10][1];
//    private static final String[] daysOfWeek = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

    public static void main(String[] args) {
        createSchedule();
    }

    private static void createSchedule() {
        Scanner scanner = new Scanner(System.in);
        readNameStudent(scanner);
        populateTimeTable(scanner);
        recordAttendance(scanner);
        printSchedule();
        scanner.close();
    }

    private static void recordAttendance(Scanner scanner) {

    }

    private static void printSchedule() {

    }

    private static void populateTimeTable(Scanner scanner) {
        String inputStr = scanner.nextLine();
        int indexTime = 0;
        int previousIndexDayOfWeek = -1;
        int intCountLessons = 1;
        while ((!inputStr.equals(".")) && (intCountLessons++ < MAX_LESSONS_PER_WEEK)) {
            char[] arrInputData = inputStr.toCharArray();
            int time = arrInputData[0] - '0';

            validateInputTimeTable(inputStr, time, scanner);

            String dayOfWeek = inputStr.substring(2);
            int indexDayOfWeek = parseDayOfWeek(dayOfWeek);

            validateDayOfWeek(indexDayOfWeek, scanner);
            validateRepeatTimeOfLesson(time, indexDayOfWeek, scanner);
            validateSequence(indexDayOfWeek, previousIndexDayOfWeek, scanner);

            if (isUniqueDay(indexDayOfWeek)) {
                indexTime = 0;
                timeTable[indexDayOfWeek][indexTime++] = time;
                previousIndexDayOfWeek = indexDayOfWeek;
            } else {
                timeTable[indexDayOfWeek][indexTime++] = time;
                validateIncreasingTime(timeTable[indexDayOfWeek], indexTime, scanner);
            }

            inputStr = scanner.nextLine();
        }
    }

    private static void validateInputTimeTable(String inputStr, int time, Scanner scanner) {
        if (inputStr.length() != MAX_LENGTH_TIME_TABLE) {
            exitFromProgram("Error input, length time table", scanner);
        } else if ((time > MAX_LESSON_TIME) || (time < MIN_LESSON_TIME)) {
            exitFromProgram("Error input, time format", scanner);
        }
    }

    private static void validateDayOfWeek(int indexDayOfWeek, Scanner scanner) {
        if (indexDayOfWeek == -1) {
            exitFromProgram("Error input, incorrect format day of week", scanner);
        }
    }

    private static void validateRepeatTimeOfLesson(int time, int indexDayOfWeek, Scanner scanner) {
        if (isRepeatTimeOfLesson(time, indexDayOfWeek)) {
            exitFromProgram("Error input, repeat time of lesson", scanner);
        }
    }

    private static void validateSequence(int indexDayOfWeek, int previousIndexDayOfWeek, Scanner scanner) {
        if (indexDayOfWeek < previousIndexDayOfWeek) {
            exitFromProgram("Error input, incorrect sequence", scanner);
        }
    }

    private static void validateIncreasingTime(int[] timeArray, int indexTime, Scanner scanner) {
        if (timeArray[indexTime - 2] > timeArray[indexTime - 1]) {
            exitFromProgram("Error input, time should be increasing", scanner);
        }
    }

    private static boolean isRepeatTimeOfLesson(int time, int indexDayOfWeek) {
        for (int i = 0; i < MAX_LESSONS_PER_WEEK; ++i) {
            if (time == timeTable[indexDayOfWeek][i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUniqueDay(int indexDayOfWeek) {
        return timeTable[indexDayOfWeek][0] == 0;
    }

    private static int parseDayOfWeek(String day) {
        return switch (day) {
            case "MO" -> 0;
            case "TU" -> 1;
            case "WE" -> 2;
            case "TH" -> 3;
            case "FR" -> 4;
            case "SA" -> 5;
            case "SU" -> 6;
            default -> -1;
        };
    }

    private static void readNameStudent(Scanner scanner) {
        for (int i = 0; i < MAX_STUDENTS; ++i) {
            String name = scanner.nextLine();
            if (name.equals(".")) {
                break;
            } else if (name.length() > MAX_LENGTH_NAME) {
                exitFromProgram("Error input, length name", scanner);
            }
            nameStudents[i] = name;
        }
    }

    private static void exitFromProgram(String exitMessage, Scanner scanner) {
        System.err.println(exitMessage);
        scanner.close();
        System.exit(-1);
    }
}
