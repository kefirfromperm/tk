package org.kefirsf.tk;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Support flash scope.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class FlashFilter implements Filter {

    private static final String FLASH_OLD_GENERATION = "FLASH_OLD_GENERATION";
    public static final String FLASH = "flash";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = null;
        if (req instanceof HttpServletRequest) {
            session = ((HttpServletRequest) req).getSession();
        }

        FlashMap flash;
        if (session != null && session.getAttribute(FLASH_OLD_GENERATION) != null) {
            //noinspection unchecked
            flash = new FlashMap((Map<String, Object>) session.getAttribute(FLASH_OLD_GENERATION));
        } else {
            flash = new FlashMap();
        }

        req.setAttribute(FLASH, flash);

        chain.doFilter(req, resp);

        if (session != null) {
            session.setAttribute(FLASH_OLD_GENERATION, flash.getYoung());
        }
    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing!
    }

    public void destroy() {
        // Nothing!
    }
}
