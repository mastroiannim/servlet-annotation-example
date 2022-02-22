package it.paleocapa.bean;

import it.paleocapa.entity.User;
import java.util.*;
import java.sql.*;

public interface UserBean {

    public List<User> getUsers() throws SQLException;

}



