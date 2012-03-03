package org.kefirsf.tk;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
            request.setAttribute(ERROR_MESSAGE, "Long twit can't be blank.");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        String text = message.trim();
        if (text.length() > TextWrapper.MAX_SIZE) {
            request.setAttribute(
                    ERROR_MESSAGE,
                    "The long twit is too long. Maximum is " +
                            String.valueOf(TextWrapper.MAX_SIZE) + " characters."
            );
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        String[] strings = wrapper.wrap(text);
        if(strings.length > TextWrapper.MAX_STRING_COUNT){
            request.setAttribute(
                    ERROR_MESSAGE,
                    "The long twit has too many strings. Maximum is " +
                            String.valueOf(TextWrapper.MAX_STRING_COUNT) + " strings."
            );
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        if (twitMessage(twitter, text, strings)) {
            response.sendRedirect(response.encodeRedirectURL("/success"));
        } else {
            request.setAttribute(
                    ERROR_MESSAGE,
                    "Sorry. Your long twit can't be sent now. Try send later."
            );
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }

    /**
     * Twit message
     *
     * @param twitter twitter object for user
     * @param message user message
     * @param strings wrapped message
     * @return true if status was sent, false otherwise
     */
    private boolean twitMessage(Twitter twitter, String message, String[] strings) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            renderer.render(strings, baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            StatusUpdate su = new StatusUpdate(statusText(message));
            su.media("text.png", bais);
            twitter.updateStatus(su);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String statusText(String message) {
        return annotate(message) + "... #lngtw "
                + ConfigurationHolder.getInstance().get("server.url");
    }

    private String annotate(String message) {
        return message.substring(0, Math.min(80, message.length())).trim();
    }
}
