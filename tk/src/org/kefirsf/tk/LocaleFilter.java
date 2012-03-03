package org.kefirsf.tk;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * Change local and safe in cookie
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class LocaleFilter implements Filter {
    private static final String LANGUAGE_PARAMETER = "lang";
    private static final String LOCALE_COOKIE = "locale";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            String lang = request.getParameter(LANGUAGE_PARAMETER);
            if (lang != null && lang.trim().length() == 2) {
                Locale locale = new Locale(lang.trim());
                Config.set(request, Config.FMT_LOCALE, locale);
                Cookie cookie = new Cookie(LOCALE_COOKIE, locale.toString());
                cookie.setMaxAge(31536000); // One year
                response.addCookie(cookie);
            } else {
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie:cookies){
                    if(LOCALE_COOKIE.equals(cookie.getName()) && cookie.getValue()!=null){
                        Config.set(request, Config.FMT_LOCALE, new Locale(cookie.getValue()));
                        break;
                    }
                }
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing!!!
    }

    public void destroy() {
        // Nothing!!!
    }
}
