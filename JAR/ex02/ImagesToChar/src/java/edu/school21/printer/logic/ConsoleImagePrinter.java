package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class ConsoleImagePrinter {

    private final String black;
    private final String white;
    private final CustomImage myImage;
    private static final String FILLER = " ";

    public ConsoleImagePrinter(InputArgs args, String path) throws IOException {
        this.black = args.getBlack();
        this.white = args.getWhite();
        this.myImage = new CustomImage(ImageIO.read(requireNonNull(ConsoleImagePrinter.class.getResource(path))));
    }


    public void print() throws IOException {
        ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false)
                .attribute(Ansi.Attribute.NONE)
                .foreground(Ansi.FColor.NONE)
                .build();

        for (int i = 0; i < myImage.getHeight(); i++) {
            for (int j = 0; j < myImage.getWidth(); j++) {
                int pixel = myImage.getBufferedImage().getRGB(j, i);

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

    protected static class CustomImage {
        private final BufferedImage bufferedImage;
        private final int width;
        private final int height;

        protected CustomImage(BufferedImage image) {
            this.bufferedImage = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
        }

        protected int getWidth() {
            return width;
        }

        protected int getHeight() {
            return height;
        }

        protected BufferedImage getBufferedImage() {
            return bufferedImage;
        }
    }
}
