package org.kefirsf.tk;

import twitter4j.Status;
import twitter4j.Twitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Render image and twit it.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class TwitServlet extends HttpServlet {
    public static final String ERROR_MESSAGE = "errorMessage";

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        TwitCommand command = new TwitCommand();

        command.setText(request.getParameter("message"));
        command.setFontColor(request.getParameter("font-color"));
        command.setBackgroundColor(request.getParameter("background-color"));

        if(!command.validate()){
            request.setAttribute(ERROR_MESSAGE, command.getErrorCode());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }

        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        command.setTwitter(twitter);

        if (command.execute()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> flash = (Map<String, Object>) request.getAttribute(FlashFilter.FLASH);
            if(flash!=null){
                Status status = command.getStatus();
                String url = MessageFormat.format(
                        "https://twitter.com/#!/{0}/status/{1,number,0}",
                        status.getUser().getScreenName(),
                        status.getId()
                );
                flash.put("statusUrl", url);
            }
            
            response.sendRedirect(request.getContextPath() + "/success");
        } else {
            request.setAttribute(ERROR_MESSAGE, command.getErrorCode());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }
}
