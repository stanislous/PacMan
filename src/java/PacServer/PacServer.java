package PacServer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stanislous
 */
@WebServlet(name = "PacServer", urlPatterns = {"/stream"})
public class PacServer extends HttpServlet {

    final gameLogic myGameLogic = new gameLogic();
    Players play;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream, charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

//            String Player = null;
//            String P1, P2, P3, P4;
//            HttpSession session = request.getSession(false);
//            if (session == null) {
//                session.setAttribute("PlayerID", myGameLogic.getPlayer());
//            } else {
//                session.getAttribute(Player);
//            }

            while (!Thread.interrupted()) {
                synchronized (myGameLogic) {

                    out.print("data: ");
                    out.println(myGameLogic.completeJsonObject());
                    out.println();
                    out.flush();
                    myGameLogic.wait();

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
            myGameLogic.keyStroke(key);
            myGameLogic.notifyAll();
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
