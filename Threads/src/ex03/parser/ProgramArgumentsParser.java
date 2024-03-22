package ex03.parser;

import ex03.parser.exception.ProgramArgumentsParserException;

public final class ProgramArgumentsParser {
    private static final String THREADS_COUNT_OPTION = "--threadsCount";
    private static final String ERROR_EMPTY_ARGS = "Args is empty, use --threadsCount=number";
    private static final String ERROR_INVALID_OPTION =
            "The option for the number of threads must be ''--threadsCount=number''";

    public static int getNumberOfThreads(String[] args) {
        if (args.length == 0) {
            throw new ProgramArgumentsParserException(ERROR_EMPTY_ARGS);
        } else if (!isValidProgramArguments(args)) {
            throw new ProgramArgumentsParserException(ERROR_INVALID_OPTION);
        }
        try {
            return Integer.parseInt(args[0].split("=")[1]);
        } catch (NumberFormatException e) {
            throw new ProgramArgumentsParserException("Invalid number format: " + e.getMessage());
        }
    }

    private static boolean isValidProgramArguments(String[] args) {
        return args.length == 1 && isValidOption(args[0].split("=")[0]);
    }

    private static boolean isValidOption(String option) {
        return option.equals(THREADS_COUNT_OPTION);
    }

    private ProgramArgumentsParser() {
    }
}
