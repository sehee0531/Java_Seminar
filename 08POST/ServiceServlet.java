package mytest;

import java.io.*;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/Test","/Test2","/Test3",""}) 

public class ServiceServlet extends HttpServlet{

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        try{
        System.out.println(request.getRequestURL()); 
        String urlString = request.getRequestURL().toString();
        String route = urlString.substring(urlString.lastIndexOf("/"));
        
        switch(route){
            case "/Test":
            this.putJson(request, response);
            break;
            case "/Test2": 
            this.putImage(request,response);
            break;
        }
    }catch(Exception e){}

        
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        try{
        System.out.println(request.getRequestURL()); 
        String urlString = request.getRequestURL().toString();
        String route = urlString.substring(urlString.lastIndexOf("/"));
        
        switch(route){
            case "/Test3":
            this.getImage(request, response);
            break;
            
        }
    }catch(Exception e){}

        
    }

    private void putJson(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        request.setCharacterEncoding("utf-8"); //Client가 나한테 주는게 utf-8파일이고
        response.setContentType("text/plain; charset=utf-8"); // 내가 Client에게 주는게 text utf-8 파일이다
        PrintWriter out = response.getWriter();

        InputStream in = request.getInputStream();
        byte[] data = new byte[1024];
        int size;

        StringBuilder message = new StringBuilder();
        while((size=in.read(data))!=-1){
            message.append(new String(data,0,size,"utf-8"));//+= 역할을 제공해줌
            System.out.println(message.toString());
        }
        out.println(message.toString());
    }

    private void putImage(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        response.setContentType("text/plain;"); //header
        InputStream filein = request.getInputStream();
        FileOutputStream fileout = new FileOutputStream("../webapps/Second/test2.jpg");
        PrintWriter out = response.getWriter();
        //FileInputStream filein = new FileInputStream("../webapps/Second/cat.jpg");

        byte[] data = new byte[1024];
        int size;

        while((size=filein.read(data))!=-1){
            fileout.write(data,0,size);
            fileout.flush();
        }
        out.write("{\"success\":1}");
        filein.close();
        out.close();
    }
    private void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        response.setContentType("image/jpeg"); //header
        OutputStream out = response.getOutputStream();
        
        String id = request.getParameter("id");
        String name = URLDecoder.decode(request.getParameter("name"),"utf-8");
        System.out.println(name);
        int size2;
        byte[] data2 = new byte[1024*8];
        FileInputStream filein = new FileInputStream(id+".jpg");
        while((size2=filein.read(data2))!=-1){
            out.write(data2,0,size2);
            out.flush();
        }
        
    }



}