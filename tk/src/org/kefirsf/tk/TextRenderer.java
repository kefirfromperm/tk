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

    public static final int WIDTH = 435;
    public static final int PADDING = 10;
    public static final int STRING_HEIGHT = 20;


    public void render(String[] strings, OutputStream out) throws IOException {
        ImageIO.write(render(strings), "PNG", out);
    }

    private RenderedImage render(String[] strings) {
        // Height
        int height = PADDING + strings.length * STRING_HEIGHT;

        BufferedImage image = new BufferedImage(WIDTH, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = image.getGraphics();

        // Fill image by white color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, height);

        // Write text
        graphics.setFont(new Font("monospaced", Font.PLAIN, 12));
        graphics.setColor(Color.BLACK);
        int index = 0;
        for (String str : strings) {
            index++;
            graphics.drawString(str, PADDING, index * STRING_HEIGHT);
        }
        graphics.dispose();

        return image;
    }

}
