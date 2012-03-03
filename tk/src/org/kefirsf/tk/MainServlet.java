package org.kefirsf.tk;

import twitter4j.Twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Main servlet. Show authorization form or twit-form.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class MainServlet extends HttpServlet {
    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        Twitter twitter = null;
        HttpSession session = request.getSession();
        if (session != null) {
            twitter = (Twitter) session.getAttribute("twitter");
        }

        if (TwitterUtils.validate(twitter)) {
            // Twit form
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } else {
            // Authorization form
            request.getRequestDispatcher("/WEB-INF/jsp/auth.jsp").forward(request, response);
        }
    }
}
