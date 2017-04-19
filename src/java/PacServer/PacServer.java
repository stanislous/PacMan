package PacServer;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author stanislous
// */
//@WebServlet(name = "PacServer", urlPatterns = {"/stream"})
//public class PacServer extends HttpServlet {
//    
//    Collector collector = new Collector();
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//            response.setContentType("text/event-stream");
//            response.flushBuffer();
//        
//            try(PrintWriter out = response.getWriter()){
//                collector.DotFormat();
//                collector.PlayerFormat();
//                out.print("OKasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//         
//       
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/stream"})
public final class PacServer extends HttpServlet {

    final Collector stock = new Collector();

   
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");

        try (final PrintWriter out = response.getWriter()) {

            while (!Thread.interrupted()) {
                synchronized (stock) {
                    stock.DotFormat();
                    stock.PlayerFormat();
                }
                
                out.println(stock);
                out.println();
                out.flush();
            }
        }
    }

    @Override
    public void destroy() {
        
    }
    
   
}
