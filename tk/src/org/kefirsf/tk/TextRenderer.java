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
    public static final int STRING_LENGTH = 59;

    public void render(String text, File file) throws IOException {
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Can't create image file.");
        }
        ImageIO.write(render(text), "PNG", file);
    }

    private RenderedImage render(String text) {
        String[] strings = cut(text);

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

    private String[] cut(String text) {
        List<String> list = new ArrayList<String>();
        for (String str : text.split("\\n")) {
            if (str.length() > STRING_LENGTH) {
                for (String s : wrap(str)) {
                    list.add(s);
                }
            } else {
                list.add(str);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Wrap string by spaces and puncts.
     *
     * @param string source string without line separators
     * @return list of strings
     */
    private List<String> wrap(String string) {
        List<String> list = new ArrayList<String>();

        SortedSet<Region> regions = new TreeSet<Region>();

        // Spaces
        Matcher sm = Pattern.compile("\\s+").matcher(string);
        while (sm.find()) {
            regions.add(new Region(sm.start(), sm.end(), false));
        }

        // Punctuation
        Matcher pm = Pattern.compile("\\p{P}").matcher(string);
        while (pm.find()) {
            regions.add(new Region(pm.start(), pm.end(), true));
        }

        int cur = 0;
        while (cur < string.length()) {
            Region region = null;
            for(Region reg: regions){
                if(reg.getStart() <= cur){
                    break;
                }

                if (reg.getStart() <= cur + STRING_LENGTH) {
                    region = reg;
                    break;
                }
            }
            
            if (region!=null) {
                if (region.isPunct()) {
                    if (region.getEnd() <= cur + STRING_LENGTH) {
                        list.add(string.substring(cur, region.getEnd()));
                        cur = region.getEnd();
                    } else {
                        list.add(string.substring(cur, region.getStart()));
                        cur = region.getStart();
                    }
                } else {
                    list.add(string.substring(cur, region.getStart()));
                    cur = region.getEnd();
                }
            } else {
                int end = Math.min(cur + STRING_LENGTH, string.length());
                list.add(string.substring(cur, end));
                cur = cur + end;
            }
        }

        return list;
    }

    private class Region implements Comparable<Region> {
        private final int start;
        private final int end;
        private final boolean punct;

        private Region(int start, int end, boolean punct) {
            this.start = start;
            this.end = end;
            this.punct = punct;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public boolean isPunct() {
            return punct;
        }

        @Override
        public int compareTo(Region o) {
            return o.start - this.start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Region region = (Region) o;

            return end == region.end && punct == region.punct && start == region.start;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            result = 31 * result + (punct ? 1 : 0);
            return result;
        }
    }
}
