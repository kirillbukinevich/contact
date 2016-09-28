//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package web;

import logic.processcommand.ActionCommand;
import logic.processcommand.ActionFactory;
import logic.configuration.ConfigurationManager;
import logic.configuration.LogConfiguration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public Controller() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        response.setContentType("text/html");
        ActionFactory client = new ActionFactory();

        ActionCommand command = client.defineCommand(request);
        LogConfiguration.LOGGER.info("start execute command");
        page = command.execute(request);
        LogConfiguration.LOGGER.info("finish execute command");

        if (page != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);

        } else {
            page = "/web/jsp/error.jsp";
            response.sendRedirect(request.getContextPath() + page);
        }

    }
}
