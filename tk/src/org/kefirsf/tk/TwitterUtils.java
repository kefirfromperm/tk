package org.kefirsf.tk;

import twitter4j.Twitter;

/**
 * Utilities for twitter.
 * @author Vitalii Samolovskikh aka Kefir
 */
public final class TwitterUtils {
    public static boolean validate(Twitter twitter){
        boolean flag = false;
        if(twitter!=null){
            try {
                twitter.verifyCredentials();
                flag = true;
            } catch (Throwable e) {
                if(Environment.get() == Environment.DEVELOPMENT){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
    
    /**
     * Prevent class initialization.
     */
    private TwitterUtils() {
    }
}
