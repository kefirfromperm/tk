package org.kefirsf.tk;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
@WebServlet(name = "DownloadServlet", urlPatterns = "/i/*")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getPathInfo();
        int ind = filename.lastIndexOf("/");
        if (ind >= 0) {
            filename = filename.substring(ind + 1);
        }
        
        File file = new File("d:\\tmp", filename);
        if (file.exists()) {
            FileUtils.copyFile(file, response.getOutputStream());
        } else {
            response.sendError(404);
        }
    }
}
