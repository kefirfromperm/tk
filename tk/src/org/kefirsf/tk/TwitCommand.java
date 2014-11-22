package org.kefirsf.tk;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import java.awt.*;
import java.io.*;
import java.text.MessageFormat;

/**
 * Contains all info about long twit.
 *
 * @author kefir
 */
public class TwitCommand implements Serializable {
    private static final int MAX_SIZE = 1300;
    private static final int MAX_STRING_COUNT = 34;
    public static final String ANNOTATION_SUFFIX = "...";

    // Input properties
    private String text = "";
    private Color fontColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private boolean hideAnnotation = false;
    private boolean addPoll = false;

    // Tune properties
    private Twitter twitter = null;

    // Output properties
    private String errorCode = "";
    private Status status = null;

    public TwitCommand() {
    }

    public boolean validate() {
        if (text.length() == 0) {
            errorCode = "message.error.blank";
            return false;
        }

        if (text.length() > MAX_SIZE) {
            errorCode = "message.error.long";
            return false;
        }

        return true;
    }

    /**
     * Twit message
     *
     * @return true if status was sent, false otherwise
     */
    public boolean execute() {
        // Wrap text
        String[] strings = TextWrapper.wrap(text);
        if (strings.length > MAX_STRING_COUNT) {
            errorCode = "message.error.too.many.strings";
            return false;
        }

        // Render image
        InputStream imageStream;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            TextRenderer.render(strings, baos, fontColor, backgroundColor);
            imageStream = new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException e) {
            errorCode = "message.error.twitter";
            return false;
        }

        // Send twit
        try {
            StatusUpdate su = new StatusUpdate(statusText(text));
            su.media("text.png", imageStream);
            status = twitter.updateStatus(su);
            return true;
        } catch (Exception e) {
            errorCode = "message.error.twitter";
            return false;
        }
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text != null) {
            this.text = text.trim();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getFontColor() {
        return ColorUtils.colorToString(fontColor);
    }

    public void setFontColor(String color) {
        if (color != null) {
            try {
                this.fontColor = Color.decode(color);
            } catch (NumberFormatException e) {
                // Nothing!
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getBackgroundColor() {
        return ColorUtils.colorToString(backgroundColor);
    }

    public void setBackgroundColor(String color) {
        if (color != null) {
            try {
                this.backgroundColor = Color.decode(color);
            } catch (NumberFormatException e) {
                // Nothing!
            }
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Status getStatus() {
        return status;
    }

    private String statusText(String message) {
        StringBuilder b = new StringBuilder();
        if(!hideAnnotation){
            int len;
            if(addPoll){
                len = 60;
            } else {
                len = 140;
            }
            b.append(annotate(message, len));
        }
        if(addPoll){
            b.append(MessageFormat.format("http://{0}/p/{1}/YES", "lngtw.com", 0));
        }
        return b.toString();
    }

    private String annotate(String message, int len) {
        if(message.length()<=len){
            return message;
        } else {
            return message.substring(0, len-ANNOTATION_SUFFIX.length()).trim()+ANNOTATION_SUFFIX;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public boolean isHideAnnotation() {
        return hideAnnotation;
    }

    public void setHideAnnotation(boolean hideAnnotation) {
        this.hideAnnotation = hideAnnotation;
    }

    public boolean isAddPoll() {
        return addPoll;
    }

    public void setAddPoll(boolean addPoll) {
        this.addPoll = addPoll;
    }
}
