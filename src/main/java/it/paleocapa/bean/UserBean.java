package it.paleocapa.bean;

import it.paleocapa.entity.User;
import java.util.*;
import java.sql.*;
import javax.servlet.http.*;

public interface UserBean {

    public List<User> getUsers() throws SQLException;
    public void addUser(HttpServletRequest request) throws SQLException;

}



