package edu.school21.printer.impl;

import edu.school21.printer.Printer;
import edu.school21.render.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.rendering(LocalDateTime.now().toString().concat(": " + message));
    }
}
