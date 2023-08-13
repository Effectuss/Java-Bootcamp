package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConsoleImagePrinter {

    private String black;
    private String white;
    private BufferedImage image;
    private final String FILLER = " ";

    public ConsoleImagePrinter(InputArgs args, String path) throws IOException {
        this.black = args.getBlack();
        this.white = args.getWhite();
//        this.image = ImageIO.read(Objects.requireNonNull(ConsoleImagePrinter.class.getResource(path)));
        this.image = ImageIO.read(new File("/opt/goinfre/englishk/Java-Bootcamp/Day04/ex02/ImagesToChar/src/resources/it.bmp"));
    }


    public void print() throws IOException {
        ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false)
                .attribute(Ansi.Attribute.NONE)
                .foreground(Ansi.FColor.NONE)
                .build();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);

                if (pixel == Color.WHITE.getRGB()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(white));
                } else if (pixel == Color.BLACK.getRGB()) {
                    coloredPrinter.setBackgroundColor(Ansi.BColor.valueOf(black));
                }
                coloredPrinter.print(FILLER);
            }
            System.out.println();
        }
    }
}
