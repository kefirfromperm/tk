package org.kefirsf.tk;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing!!!
    }

    @Override
    public void destroy() {
        // Nothing!!!
    }
}
