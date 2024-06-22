package edu.school21;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.preprocessor.impl.PreProcessorToUpperImpl;
import edu.school21.printer.Printer;
import edu.school21.printer.impl.PrinterWithPrefixImpl;
import edu.school21.render.Renderer;
import edu.school21.render.impl.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // implementation logic without spring context
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");

        // implementation logic use spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer1 = context.getBean("printerWithPrefix", Printer.class);
        printer1.print("Hello!");
    }

}
