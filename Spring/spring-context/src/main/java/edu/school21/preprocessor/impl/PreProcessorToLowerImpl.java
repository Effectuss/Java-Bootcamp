package edu.school21.preprocessor.impl;

import edu.school21.preprocessor.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String process(String inputString) {
        return inputString.toLowerCase();
    }
}
