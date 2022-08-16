package mytest;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/Test","/Test2",""}) 

public class ServiceSuvlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        try{
        System.out.println(request.getRequestURL()); 
        String urlString = request.getRequestURL().toString();
        String route = urlString.substring(urlString.lastIndexOf("/"));
        
        switch(route){
            case "/Test":
            this.getJson(request, response);
            break;
            case "/Test2":
            this.getImage(request,response);
            break;
        }
    }catch(Exception e){}

        
    }

    private void getJson(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        response.setContentType("text/plain; charset=utf-8"); //header
        PrintWriter out = response.getWriter();
        out.println("{\"success\":1,\"name\":\"정세희\"}");
    }

    private void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        response.setContentType("image/jpeg"); //header
        OutputStream out = response.getOutputStream();
        FileInputStream filein = new FileInputStream("../webapps/first/cat.jpg");

        byte[] data = new byte[1024];
        int size;

        while((size=filein.read(data))!=-1){
            out.write(data,0,size);
            out.flush();
        }
        filein.close();
        out.close();
    }

}