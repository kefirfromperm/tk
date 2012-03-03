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
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            Twitter twitter = null;
            HttpSession session = request.getSession();
            if (session != null) {
                twitter = (Twitter) session.getAttribute("twitter");
            }

            if (TwitterUtils.validate(twitter)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath()+ "/");
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
