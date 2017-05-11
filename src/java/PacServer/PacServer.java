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
 * @author E/13/377 & E/13/270
 */
@WebServlet(name = "PacServer", urlPatterns = {"/stream"})
public class PacServer extends HttpServlet {

    final gameLogic myGameLogic = new gameLogic();
    Players play;
    static int pl = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream, charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();      //get the session object
            if (session.isNew()) {
                session.setAttribute("player", pl++ + "");   // if it is a new session, set attributes
            } else {
            }                                           //else do nothing

            while (!Thread.interrupted()) {
                synchronized (myGameLogic) {

                    out.print("data: ");
                    out.println(myGameLogic.completeJsonObject());
                    out.println();
                    out.flush();
                    myGameLogic.wait();            //wait any thread while the game window is updated
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
        HttpSession session = request.getSession();
        String attribute = (String) session.getAttribute("player");          //getting the unique which is being played

        synchronized (myGameLogic) {
            if (pl > 4) {   
            myGameLogic.keyStroke(key, "P" + attribute);                 //call game logic and update
            myGameLogic.notifyAll();                                  //awaken all the threads which are wait in doGet method.
        }
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
