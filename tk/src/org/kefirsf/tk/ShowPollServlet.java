package org.kefirsf.tk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kefir
 */
@WebServlet(name = "ShowPollServlet", urlPatterns = "/poll/")
public class ShowPollServlet extends HttpServlet {
    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

    }
}
