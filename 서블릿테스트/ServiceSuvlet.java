package mytest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Test")

public class ServiceSuvlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        response.setContentType("text/plain; charset=utf-8"); //header
        PrintWriter out = response.getWriter();
        out.println("{\"success\":1}");


    }
}