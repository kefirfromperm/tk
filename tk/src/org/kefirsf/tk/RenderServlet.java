package org.kefirsf.tk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
@WebServlet(name = "RenderServlet", urlPatterns = "/render")
public class RenderServlet extends HttpServlet {
    private TextRenderer renderer = new TextRenderer();
    private FileGenerator fileGenerator = new FileGenerator();

    public RenderServlet() {
        fileGenerator.setDirectory(new File("d:\\tmp"));
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message != null) {
            File file = fileGenerator.newFile();
            renderer.render(message, file);
            response.sendRedirect("/i/"+file.getName());
        } else {
            response.sendRedirect("/");
        }
    }
}
