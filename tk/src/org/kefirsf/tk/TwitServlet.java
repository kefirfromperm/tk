package org.kefirsf.tk;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Render image and twit it.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class TwitServlet extends HttpServlet {
    public static final String ERROR_MESSAGE = "errorMessage";
    private TextRenderer renderer = new TextRenderer();
    private final TextWrapper wrapper = new TextWrapper();

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");

        String message = request.getParameter("message");
        if (message == null || message.trim().equals("")) {
            request.setAttribute(ERROR_MESSAGE, "message.error.blank");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        String text = message.trim();
        if (text.length() > TextWrapper.MAX_SIZE) {
            request.setAttribute(ERROR_MESSAGE, "message.error.long");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        String[] strings = wrapper.wrap(text);
        if (strings.length > TextWrapper.MAX_STRING_COUNT) {
            request.setAttribute(ERROR_MESSAGE, "message.error.too.many.strings");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        Color fontColor = Color.black;
        try{
            fontColor = Color.decode(request.getParameter("font-color"));
        }catch (NumberFormatException e){
            // Nothing!
        }

        Color backgroundColor = Color.white;
        try{
            backgroundColor = Color.decode(request.getParameter("background-color"));
        }catch (NumberFormatException e){
            // Nothing!
        }

        Status status = twitMessage(twitter, text, strings, fontColor, backgroundColor);
        if (status!=null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> flash = (Map<String, Object>) request.getAttribute(FlashFilter.FLASH);
            if(flash!=null){
                String url = MessageFormat.format(
                        "https://twitter.com/#!/{0}/status/{1,number,0}",
                        status.getUser().getScreenName(),
                        status.getId()
                );
                flash.put("statusUrl", url);
            }
            
            response.sendRedirect(request.getContextPath() + "/success");
        } else {
            request.setAttribute(ERROR_MESSAGE, "message.error.twitter");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }

    /**
     * Twit message
     *
     *
     *
     * @param twitter twitter object for user
     * @param message user message
     * @param strings wrapped message
     * @param fontColor font color
     * @param backgroundColor background color
     * @return true if status was sent, false otherwise
     */
    private Status twitMessage(Twitter twitter, String message, String[] strings, Color fontColor, Color backgroundColor) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            renderer.render(strings, baos, fontColor, backgroundColor);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            StatusUpdate su = new StatusUpdate(statusText(message));
            su.media("text.png", bais);
            return twitter.updateStatus(su);
        } catch (Exception e) {
            return null;
        }
    }

    private String statusText(String message) {
        ConfigurationHolder conf = ConfigurationHolder.getInstance();
        return annotate(message) + "... " + conf.get("app.tag") + " " + conf.get("server.url");
    }

    private String annotate(String message) {
        return message.substring(0, Math.min(80, message.length())).trim();
    }
}
