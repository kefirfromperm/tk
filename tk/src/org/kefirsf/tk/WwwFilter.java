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

            // Reconstruct original requesting URL
            StringBuilder url =  new StringBuilder();
            url.append(scheme).append("://").append(serverName);

            if ((serverPort != 80) && (serverPort != 443)) {
                url.append(":").append(serverPort);
            }

            if(contextPath!=null && contextPath.length()>0){
                url.append(contextPath).append("/");
            }

            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", url.toString());
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
