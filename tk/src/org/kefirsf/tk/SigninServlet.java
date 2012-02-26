package org.kefirsf.tk;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Signin servlet
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/signin"})
public class SigninServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = new TwitterFactory().getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {
            String callbackUrl = new StringBuilder()
                    .append(ConfigurationHolder.getInstance().get("server.url"))
                    .append("/callback")
                    .toString();
            System.out.println(callbackUrl);

            RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl);
            request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }
}
