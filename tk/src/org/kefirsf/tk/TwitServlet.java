package org.kefirsf.tk;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Render image and twit it.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class TwitServlet extends HttpServlet {
    private static final String ERROR_MESSAGE = "errorMessage";
    public static final String COMMAND_ATTR = "TWIT_COMMAND";

    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();

        TwitCommand command = (TwitCommand) session.getAttribute(COMMAND_ATTR);
        if (command != null && request.getParameter("message") == null) {
            session.setAttribute(COMMAND_ATTR, null);
        } else {
            command = new TwitCommand();
            command.setText(request.getParameter("message"));
            command.setFontColor(request.getParameter("font-color"));
            command.setBackgroundColor(request.getParameter("background-color"));
        }

        if (!command.validate()) {
            request.setAttribute("command", command);
            request.setAttribute(ERROR_MESSAGE, command.getErrorCode());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        Twitter twitter = (Twitter) session.getAttribute("twitter");
        if (!TwitterUtils.validate(twitter)) {
            session.setAttribute(COMMAND_ATTR, command);
            signin(request, response);
            return;
        }

        command.setTwitter(twitter);
        if (command.execute()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> flash = (Map<String, Object>) request.getAttribute(FlashFilter.FLASH);
            if (flash != null) {
                Status status = command.getStatus();
                String url = MessageFormat.format(
                        "https://twitter.com/#!/{0}/status/{1,number,0}",
                        status.getUser().getScreenName(),
                        status.getId()
                );
                flash.put("statusUrl", url);
            }

            response.sendRedirect(request.getContextPath() + "/success");
        } else {
            request.setAttribute("command", command);
            request.setAttribute(ERROR_MESSAGE, command.getErrorCode());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }

    private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Twitter twitter;
        twitter = new TwitterFactory().getInstance();
        session.setAttribute("twitter", twitter);
        try {
/*
            RequestToken requestToken = twitter.getOAuthRequestToken(MessageFormat.format(
                    "http://{0}:{1}{2}/callback",
                    request.getServerName(),
                    String.valueOf(request.getLocalPort()),
                    request.getContextPath()
            ));
*/

            RequestToken requestToken = twitter.getOAuthRequestToken();
            session.setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }
}
