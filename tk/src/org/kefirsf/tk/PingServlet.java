package org.kefirsf.tk;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for ping application.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class PingServlet extends HttpServlet {
    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.getWriter().write("I'm OK!");
        response.flushBuffer();
    }
}
