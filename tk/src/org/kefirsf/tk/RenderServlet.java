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
@WebServlet(name = "RenderServlet", urlPatterns = "/render")
public class RenderServlet extends HttpServlet {
    private TextRenderer renderer = new TextRenderer();

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            renderer.render(message, baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
            if (twitter != null) {
                try {
                    StatusUpdate su = new StatusUpdate(
                            message.substring(0, Math.min(30, message.length())) + "... "
                                    + ConfigurationHolder.getInstance().get("server.url")
                    );
                    su.media("text.png", bais);
                    twitter.updateStatus(su);
                } catch (TwitterException e) {
                    throw new ServletException(e);
                }
                response.sendRedirect("/");
            } else {
                throw new ServletException("Twitter not found!");
            }
        } else {
            response.sendRedirect("/");
        }
    }
}
