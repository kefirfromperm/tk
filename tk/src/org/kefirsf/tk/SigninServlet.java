package org.kefirsf.tk;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Signin servlet
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class SigninServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = new TwitterFactory().getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {

            String callbackUrl = new StringBuilder()
                    .append("http://").append(request.getServerName())
                    .append(":").append(String.valueOf(request.getLocalPort()))
                    .append(request.getContextPath())
                    .append("/callback")
                    .toString();
            RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl);

            //RequestToken requestToken = twitter.getOAuthRequestToken();
            request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }
}
