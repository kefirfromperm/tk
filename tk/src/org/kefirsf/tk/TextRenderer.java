package org.kefirsf.tk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Render text to image.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class TextRenderer {

    private static final int WIDTH = 870;
    private static final int PADDING = 20;
    private static final int STRING_HEIGHT = 40;
    private static final int FONT_SIZE = 36;


    public static void render(String[] strings, OutputStream out, Color fontColor, Color backgroundColor) throws IOException {
        ImageIO.write(render(strings, fontColor, backgroundColor), "PNG", out);
    }

    private static RenderedImage render(String[] strings, Color fontColor, Color backgroundColor) {
        // Height
        int height = PADDING + strings.length * STRING_HEIGHT;

        BufferedImage image = new BufferedImage(WIDTH, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        // Fill image by white color
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, WIDTH, height);

        // Write text
        graphics.setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
        graphics.setColor(fontColor);
        int index = 0;
        for (String str : strings) {
            index++;
            graphics.drawString(str, PADDING, index * STRING_HEIGHT);
        }
        graphics.dispose();

        return image;
    }
}
