package edu.school21.render.impl;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.render.Renderer;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void rendering(String outputString) {
        System.out.println(preProcessor.process(outputString));
    }
}
