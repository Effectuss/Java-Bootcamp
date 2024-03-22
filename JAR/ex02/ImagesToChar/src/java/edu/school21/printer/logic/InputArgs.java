package edu.school21.printer.logic;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(separators = "=")
public class InputArgs {

    @Parameter(names = "--white", required = true, description = "Color for white pixel", validateWith = ColorValidator.class)
    private String white;

    @Parameter(names = "--black", required = true, description = "Color for black pixel", validateWith = ColorValidator.class)
    private String black;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }


    public static class ColorValidator implements IParameterValidator {
        @Override
        public void validate(String name, String value) throws ParameterException {
            if (!isValidColor(value)) {
                throw new ParameterException("The color should be in uppercase " + name + ": " + value);
            }
        }

        private boolean isValidColor(String value) {
            for (int i = 0; i < value.length(); i++) {
                if (!Character.isUpperCase(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
