
package it.paleocapa.controller;

import it.paleocapa.entity.*;
import it.paleocapa.bean.*;

import com.google.gson.*;
import com.google.gson.reflect.*;


import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;


@WebServlet(name = "WebController", urlPatterns = {"/WebController"})
public class WebController extends HttpServlet {
  
  UserBeanImpl ubi;
  
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    ServletContext sc = getServletContext(); 
    ubi = new UserBeanImpl(sc);
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    Gson gson = new Gson();

    List<User> userList = null;
    try{
        userList = ubi.getUsers();
    }catch(Exception we){
        we.printStackTrace();
    }

    if (userList != null) {
        response.setContentType("application/json");
        gson.toJson(userList, new TypeToken<ArrayList<User>>() {}.getType(), response.getWriter());
    }else {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UserBeanImpl newUbi = new UserBeanImpl(getServletContext());
    try {
      newUbi.addUser(req);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

      /*{firstName:"hello", lastName:"ciccio", email:"mail@mail.com", password:"segreto"}*/
      /*
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
 
        System.out.println("Payload elements:");
        for (Entry<String, JsonElement> entry : data.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue().getAsString());
        }
         
        System.out.println("-------------------------------------");
         
        String firstName = data.get("firstName").getAsString();
        String lastName = data.get("lastName").getAsString();
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();
        //int age = data.get("age").getAsInt();
        //byte[] photo = Base64.getDecoder().decode(data.get("photo").getAsString());
         
        System.out.format("firstName=%s, lastName=%s, email=%s, password=%s %n", firstName, lastName, email, password);
        String query=String.format("INSERT INTO user (firstName, lastName, email, password) VALUES (%s, %s, %s,%s);", firstName, lastName, email, password);
        */
  }

}