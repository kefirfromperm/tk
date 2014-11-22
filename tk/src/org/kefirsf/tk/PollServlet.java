package org.kefirsf.tk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet for polling.
 * You can choose an answer, YES or NO
 */
@WebServlet(name = "PollServlet", urlPatterns = "/p/*")
public class PollServlet extends HttpServlet {
    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

    }
}
