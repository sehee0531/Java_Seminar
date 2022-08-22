package mytest;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;


import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/Test",""}) // annotation이다. ()안에 들어가는 것은 url주소 , {"/Test", "/Test2"} {"/Test", "/Test2", ""} 차이가 없다..?
@MultipartConfig
public class ServiceServlet extends HttpServlet{
   
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      try{
         System.out.println(request.getRequestURL());// request.getRequestURL() : 클라이언트가 지정한 전체 url이 넘어온다
         String urlString = request.getRequestURL().toString();
         String route = urlString.substring(urlString.lastIndexOf("/")); // indexOf, lastIndexOf 어디서부터 자를건가?
         
         switch(route){
            case "/Test" : 
               this.putJsonImage(request, response);
               break;
         }
      }catch(Exception e){}
   }


   private void putJsonImage(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
      // IOException을 쓰면 해결되긴 하지만 IOException이 아닌 다른 예외를 잡고 싶다면 IOException이 발생하는 곳에 try catch문을 사용하면 해결
      PrintWriter out = response.getWriter();

      MultipartExtractor extractor = new MultipartExtractor(request);
      String jsonString = extractor.getFormData("data");
      byte[] data = extractor.getBinaryData("file1");

      //FileOutputStream jsonfos = new FileOutputStream("../webapps/third/data.json");
      FileOutputStream imagefos = new FileOutputStream("../webapps/third/image1.jpg");
      //jsonfos.write(jsonString.getBytes("utf-8"));
      //jsonfos.flush();
      //jsonfos.close();

      
      imagefos.write(data,0,data.length);
      imagefos.flush();


      /*Gson gson = new Gson();
      String Sdata = gson.fromJson(jsonString, Persons.class);
      // ArrayList<String> namearray = new ArrayList<>();
      for(Person person : Sdata.persons)
      {
            System.out.println(person.name);
      }

      System.out.println(jsonString);
      System.out.println(data.length);*/
      System.out.println(jsonString);
      out.println("{\"success\":1}");
   }
   /* public void doPost(){
      
   } */
}
