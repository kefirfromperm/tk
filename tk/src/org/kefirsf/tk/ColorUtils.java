package org.kefirsf.tk;

import java.awt.*;
import java.io.Serializable;

public class ColorUtils implements Serializable {
    public static String colorToString(Color color) {
        return String.format("#%06x", color.getRGB() & 0xffffff);
    }
}