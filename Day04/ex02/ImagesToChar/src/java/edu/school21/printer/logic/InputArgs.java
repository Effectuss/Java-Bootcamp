package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(separators = "=")
public class InputArgs {
    @Parameter(description = "Main parameter")
    private List<String> parameters;

    @Parameter(names = "--white", required = true, description = "Color for white pixel")
    private String white;

    @Parameter(names = "--black", required = true, description = "Color for black pixel")
    private String black;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
