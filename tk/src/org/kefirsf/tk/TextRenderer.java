package org.kefirsf.tk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Render text to image.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class TextRenderer {

    public static final int WIDTH = 435;
    public static final int PADDING = 10;
    public static final int STRING_HEIGHT = 20;
    public static final int STRING_LENGTH = 60;

    public void render(String text, File file) throws IOException {
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Can't create image file.");
        }
        ImageIO.write(render(text), "PNG", file);
    }

    private RenderedImage render(String text) {
        String[] strings = cut(text);

        // Height
        int height = 2 * PADDING + strings.length * STRING_HEIGHT;

        BufferedImage image = new BufferedImage(WIDTH, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = image.getGraphics();

        // Fill image by white color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, height);

        // Write text
        graphics.setColor(Color.BLACK);
        int index = 0;
        for (String str : strings) {
            index++;
            graphics.drawString(str, PADDING, index * STRING_HEIGHT + PADDING);
        }

        return image;
    }

    private String[] cut(String text) {
        return text.split("\\n");
    }
}
