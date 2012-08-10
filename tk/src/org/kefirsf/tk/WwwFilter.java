package org.kefirsf.tk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kefir
 */
public class WwwFilter implements Filter {

    public static final String WWW_PREFIX = "www.";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (    req instanceof HttpServletRequest &&
                resp instanceof HttpServletResponse &&
                req.getServerName() != null &&
                req.getServerName().toLowerCase().startsWith(WWW_PREFIX)) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            String scheme = request.getScheme();             // http
            String serverName = request.getServerName();     // hostname.com
            serverName = serverName.substring(WWW_PREFIX.length());
            int serverPort = request.getServerPort();        // 80
            String contextPath = request.getContextPath();   // /mywebapp
            String servletPath = request.getServletPath();   // /servlet/MyServlet
            String pathInfo = request.getPathInfo();         // /a/b;c=123
            String queryString = request.getQueryString();          // d=789

            // Reconstruct original requesting URL
            StringBuilder url =  new StringBuilder();
            url.append(scheme).append("://").append(serverName);

            if ((serverPort != 80) && (serverPort != 443)) {
                url.append(":").append(serverPort);
            }

            url.append(contextPath).append(servletPath);

            if (pathInfo != null) {
                url.append(pathInfo);
            }
            if (queryString != null) {
                url.append("?").append(queryString);
            }

            response.sendRedirect(url.toString());
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
