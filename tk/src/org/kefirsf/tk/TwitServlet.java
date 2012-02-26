package org.kefirsf.tk;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "TwitServlet", urlPatterns = "/twit")
public class TwitServlet extends HttpServlet {
    public static final int MAX_SIZE = 65535;
    private TextRenderer renderer = new TextRenderer();

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message == null || message.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Long twit can't be null or empty.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (message.length() > MAX_SIZE) {
            request.setAttribute(
                    "errorMessage",
                    "The long twit is too long. Maximum is " + String.valueOf(MAX_SIZE) + " characters."
            );
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            if (twitMessage(request, message)) {
                response.sendRedirect("http://twitter.com");
            } else {
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
            request.setAttribute("errorMessage", e.getMessage());
            return false;
        }
    }
}
