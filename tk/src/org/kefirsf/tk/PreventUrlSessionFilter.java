package org.kefirsf.tk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class PreventUrlSessionFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            chain.doFilter(request, new PreventUrlSessionResponse(response));
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing!
    }

    public void destroy() {
        // Nothing!
    }

    private class PreventUrlSessionResponse extends HttpServletResponseWrapper {
        public PreventUrlSessionResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public String encodeURL(String url) {
            return removeSession(super.encodeURL(url));
        }

        @Override
        public String encodeRedirectURL(String url) {
            return removeSession(super.encodeRedirectURL(url));
        }

        @Override
        public String encodeUrl(String url) {
            return removeSession(super.encodeUrl(url));
        }

        @Override
        public String encodeRedirectUrl(String url) {
            return removeSession(super.encodeRedirectUrl(url));
        }

        private String removeSession(String url) {
            String str = url;
            if (str != null && str.lastIndexOf(';') >= 0) {
                str = str.substring(0, str.lastIndexOf(';'));
            }
            return str;
        }
    }
}
