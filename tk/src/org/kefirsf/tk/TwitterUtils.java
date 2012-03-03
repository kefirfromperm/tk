package org.kefirsf.tk;

import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Utilities for twitter.
 * @author Vitalii Samolovskikh aka Kefir
 */
public final class TwitterUtils {
    public static boolean validate(Twitter twitter){
        boolean flag = false;
        if(twitter!=null){
            try {
                twitter.getOAuthAccessToken();
                flag = true;
            } catch (TwitterException e) {
                // Nothing!
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
