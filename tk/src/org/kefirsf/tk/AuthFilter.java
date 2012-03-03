package org.kefirsf.tk;

import twitter4j.Twitter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Check if user did not authorize in twitter then redirect to auth page.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Twitter twitter = null;
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session = request.getSession();
            if (session != null) {
                twitter = (Twitter) session.getAttribute("twitter");
            }
        }

        if (TwitterUtils.validate(twitter)) {
            chain.doFilter(req, resp);
        } else {
            if (resp instanceof HttpServletResponse) {
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect(response.encodeRedirectURL("/"));
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing!
    }

    public void destroy() {
        // Nothing!
    }
}
