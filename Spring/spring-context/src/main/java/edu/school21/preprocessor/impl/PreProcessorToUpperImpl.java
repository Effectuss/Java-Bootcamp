package edu.school21.preprocessor.impl;

import edu.school21.preprocessor.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String inputString) {
        return inputString.toUpperCase();
    }
}
