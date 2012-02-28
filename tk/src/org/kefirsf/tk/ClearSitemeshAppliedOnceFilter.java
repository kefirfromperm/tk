package org.kefirsf.tk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Bugfix for sitemesh error decoration.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class ClearSitemeshAppliedOnceFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            request.removeAttribute("com.opensymphony.sitemesh.APPLIED_ONCE");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
