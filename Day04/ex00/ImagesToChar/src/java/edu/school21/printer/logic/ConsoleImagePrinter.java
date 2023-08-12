package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConsoleImagePrinter {
    private final char blackPixel;
    private final char whitePixel;
    private final File imageFile;

    public ConsoleImagePrinter(File imageFile, char whitePixel, char blackPixel) {
        this.blackPixel = blackPixel;
        this.whitePixel = whitePixel;
        this.imageFile = imageFile;
    }


    public void print() throws IOException {
        Image image = new Image(ImageIO.read(imageFile));
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getBufferedImage().getRGB(j, i);
                if (pixel == Color.BLACK.getRGB()) {
                    System.out.print(blackPixel);
                } else if (pixel == Color.WHITE.getRGB()) {
                    System.out.print(whitePixel);
                }
            }
            System.out.println();
        }
    }

    protected static class Image {
        private final BufferedImage bufferedImage;
        private final int width;
        private final int height;

        protected Image(BufferedImage image) {
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
