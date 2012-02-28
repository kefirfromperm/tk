package org.kefirsf.tk;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWrapper {
    public static final int STRING_LENGTH = 59;
    public static final int MAX_STRING_COUNT = 34;
    public static final int MAX_SIZE = 2000;

    public TextWrapper() {
    }

    public String[] wrap(String text) {
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
    private List<String> wrap1(String string) {
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
            for (Region reg : regions) {
                if (reg.getStart() <= cur) {
                    break;
                }

                if (reg.getStart() <= cur + STRING_LENGTH) {
                    region = reg;
                    break;
                }
            }

            if (region != null) {
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
                cur = end;
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