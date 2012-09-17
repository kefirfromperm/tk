package org.kefirsf.tk;

/**
 * Development or production or test
 *
 * @author kefir
 */
public final class Environment {
    public static final int PRODUCTION = 0;
    public static final int DEVELOPMENT = 1;
    public static final int TEST = 2;

    private static int value;

    static {
        value = PRODUCTION;

        try {
            String testFlag = System.getProperty("tk.test");
            String developmentFlag = System.getProperty("tk.development");

            if (testFlag != null && Boolean.valueOf(testFlag)) {
                value = TEST;
            } else if (developmentFlag != null && Boolean.valueOf(developmentFlag)) {
                value = DEVELOPMENT;
            }
        } catch (SecurityException e) {
            // Nothing!
        }
    }

    private Environment() {
    }

    public static int get() {
        return value;
    }
}
