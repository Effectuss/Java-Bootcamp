package edu.school21.render.impl;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.render.Renderer;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void rendering(String outputString) {
        System.err.println(preProcessor.process(outputString));
    }
}
