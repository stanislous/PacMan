package PacServer;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/event-stream");
            response.flushBuffer();
        
            try(PrintWriter out = response.getWriter()){
                out.print("data:");
                out.print(collector.DotFormat());
                out.print(collector.PlayerFormat());
                out.println();
                out.flush();
            
        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
