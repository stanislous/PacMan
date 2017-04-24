package PacServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stanislous
 */
@WebServlet(name = "PacServer", urlPatterns = {"/stream"})
public class PacServer extends HttpServlet {

    Collector collector = new Collector();

    final gameLogic myGameLogic = new gameLogic();

    String temp;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");

        try (PrintWriter out = response.getWriter()) {
            out.println("Access doGet!!");
            while (!Thread.interrupted()) {
                out.println();
                out.flush();
                Logger.getGlobal().log(Level.INFO, "Sent " + temp);
                synchronized (myGameLogic) {
                    myGameLogic.wait();
                    out.print("data: ");
                    out.println(temp);
                }
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("keypress");

        synchronized (myGameLogic) {
            temp = collector.objectSender().toString();
            myGameLogic.keyStroke(key);
            myGameLogic.notifyAll();
            Logger.getGlobal().log(Level.INFO, "Received " + temp);
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
