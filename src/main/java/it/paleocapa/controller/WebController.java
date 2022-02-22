
package it.paleocapa.controller;

import it.paleocapa.entity.*;
import it.paleocapa.bean.*;

import com.google.gson.*;
import com.google.gson.reflect.*;


import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


@WebServlet(name = "WebController", urlPatterns = {"/WebController"})
public class WebController extends HttpServlet {

  UserBeanImpl ubi = new UserBeanImpl(getServletContext());

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    Gson gson = new Gson();
    String value = null;

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

}