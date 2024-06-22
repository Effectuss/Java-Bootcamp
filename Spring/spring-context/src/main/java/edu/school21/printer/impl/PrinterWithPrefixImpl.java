package edu.school21.printer.impl;

import edu.school21.printer.Printer;
import edu.school21.render.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;

    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.rendering(prefix.concat(message));
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
