package org.kefirsf.tk;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OAuth callback servlet for twitter authorization.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        if (twitter != null && requestToken != null && verifier != null) {
            try {
                twitter.getOAuthAccessToken(requestToken, verifier);
                request.getSession().removeAttribute("requestToken");
            } catch (TwitterException e) {
                throw new ServletException(e);
            }
        }

        if (request.getSession().getAttribute(TwitServlet.COMMAND_ATTR) != null) {
            response.sendRedirect(request.getContextPath() + "/twit");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
