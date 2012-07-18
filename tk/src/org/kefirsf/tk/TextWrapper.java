package org.kefirsf.tk;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextWrapper {
    private static final int STRING_LENGTH = 37;

    private TextWrapper() {
    }

    public static String[] wrap(String text) {
        List<String> list = new ArrayList<String>();
        for (String str : text.split("\\n")) {
            if (str.length() > STRING_LENGTH) {
                for (String s : wrap1(str)) {
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
    private static List<String> wrap1(String string) {
        List<String> list = new ArrayList<String>();

        SortedSet<Region> regions = new TreeSet<Region>();

        // Spaces
        Matcher sm = Pattern.compile("\\s+").matcher(string);
        while (sm.find()) {
            regions.add(new Region(sm.start(), sm.end(), false));
        }

        // Punctuation
        Matcher pm = Pattern.compile("\\p{P}+").matcher(string);
        while (pm.find()) {
            regions.add(new Region(pm.start(), pm.end(), true));
        }

        int cur = 0;
        int strLen = string.length();
        while (cur < strLen) {
            if (strLen - cur > STRING_LENGTH) {
                Region region = null;
                for (Region reg : regions) {
                    if (reg.getStart() <= cur) {
                        break;
                    }

                    if (reg.getStart() <= cur + STRING_LENGTH && !reg.isPunct()) {
                        region = reg;
                        break;
                    }

                    if (reg.getEnd() <= cur + STRING_LENGTH && reg.isPunct()) {
                        region = reg;
                        break;
                    }
                }

                if (region != null) {
                    if (region.isPunct()) {
                        list.add(string.substring(cur, region.getEnd()));
                        cur = region.getEnd();
                    } else {
                        list.add(string.substring(cur, region.getStart()));
                        cur = region.getEnd();
                    }
                } else {
                    int end = Math.min(cur + STRING_LENGTH, strLen);
                    list.add(string.substring(cur, end));
                    cur = end;
                }
            } else {
                list.add(string.substring(cur, strLen));
                cur = strLen;
            }
        }

        return list;
    }

    private static class Region implements Comparable<Region> {
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