package ex05;

import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Program {
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_LENGTH_NAME = 10;
    private static final int MAX_LESSONS_PER_WEEK = 10;
    private static final int MAX_LENGTH_TIME_TABLE = 4;
    private static final int MIN_LESSON_TIME = 1;
    private static final int MAX_LESSON_TIME = 6;
    private static final String[] nameStudents = new String[MAX_STUDENTS];
    private static final String[] daysOfWeek = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
    private static final int[][] timeTable = new int[daysOfWeek.length][MAX_LESSONS_PER_WEEK];

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

    private static void populateTimeTable(Scanner scanner) {
        String inputStr = scanner.nextLine();
        int indexTime = 0;
        int previousIndexDayOfWeek = -1;
        int intCountLessons = 1;
        while (!inputStr.equals(".") && intCountLessons < MAX_LESSONS_PER_WEEK) {
            char[] arrInputData = inputStr.toCharArray();
            if (inputStr.length() != MAX_LENGTH_TIME_TABLE) {
                exitFromProgram("Error input, length time table");
            }
            int time = arrInputData[0] - '0';
            if ((time > MAX_LESSON_TIME) || (time < MIN_LESSON_TIME)) {
                exitFromProgram("Error input, time format");
            }
            String dayOfWeek = inputStr.substring(2);
            int indexDayOfWeek = findIndexDayOfWeek(dayOfWeek);
            if (isRepeatTimeOfLesson(time, indexDayOfWeek)) {
                exitFromProgram("Error input, repeat time of lesson");
            }
            if (indexDayOfWeek < previousIndexDayOfWeek) {
                exitFromProgram("Error input, incorrect sequence");
            }
            if (isUniqueDay(indexDayOfWeek)) {
                indexTime = 0;
                timeTable[indexDayOfWeek][indexTime++] = time;
                previousIndexDayOfWeek = indexDayOfWeek;
            } else {
                timeTable[indexDayOfWeek][indexTime++] = time;
            }
            inputStr = scanner.nextLine();
            ++intCountLessons;
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

    private static int findIndexDayOfWeek(String dayOfWeek) {
        int indexDay = -1;
        for (int i = 0; i < daysOfWeek.length; ++i) {
            if (daysOfWeek[i].equals(dayOfWeek)) {
                indexDay = i;
            }
        }
        if (indexDay == -1) {
            exitFromProgram("Error input, incorrect format day of week");
        }
        return indexDay;
    }

    private static void readNameStudent(Scanner scanner) {
        for (int i = 0; i < MAX_STUDENTS; ++i) {
            String name = scanner.nextLine();
            if (name.equals(".")) {
                break;
            } else if (name.length() > MAX_LENGTH_NAME) {
                exitFromProgram("Error input, length name");
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

