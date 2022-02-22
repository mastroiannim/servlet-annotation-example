package it.paleocapa.bean;

import it.paleocapa.entity.User;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserBeanImpl implements UserBean {

    public static ServletContext context;

    public UserBeanImpl(ServletContext context){
        UserBeanImpl.context = context;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + context.getRealPath("/") + "WEB-INF/Vuoto.accdb")) {
            return connection;
        } catch (Exception e){
            System.out.println("Errore: Impossibile creare connessione");
        }
        return null;
    }

    public List<User> getUsers() throws SQLException {
        List<User> returnValue = new ArrayList<>();
        
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Users")) {
                while(resultSet.next()) {
                    returnValue.add(new User(resultSet));
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }


}
