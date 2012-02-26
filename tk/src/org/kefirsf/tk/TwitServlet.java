package org.kefirsf.tk;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

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
    public static final int MAX_SIZE = 4095;
    public static final String ERROR_MESSAGE = "errorMessage";
    private TextRenderer renderer = new TextRenderer();

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message == null || message.trim().isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, "Long twit can't be blank.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (message.length() > MAX_SIZE) {
            request.setAttribute(
                    ERROR_MESSAGE,
                    "The long twit is too long. Maximum is " + String.valueOf(MAX_SIZE) + " characters."
            );
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            if (twitMessage(request, message)) {
                response.sendRedirect("http://twitter.com");
            } else {
                request.setAttribute(
                        ERROR_MESSAGE,
                        "Sorry. Your long twit can't be sent now. Try send later."
                );
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    private boolean twitMessage(HttpServletRequest request, String message) throws IOException, ServletException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        renderer.render(message, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        try {
            StatusUpdate su = new StatusUpdate(
                    message.substring(0, Math.min(30, message.length())) + "... "
                            + ConfigurationHolder.getInstance().get("server.url")
            );
            su.media("text.png", bais);
            twitter.updateStatus(su);
            return true;
        } catch (TwitterException e) {
            return false;
        }
    }
}
