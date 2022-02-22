package it.paleocapa.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(ResultSet resultSet) throws SQLException {
        this.userId = resultSet.getInt(1);
        this.firstName = resultSet.getString(2);
        this.lastName = resultSet.getString(3);
        this.email = resultSet.getString(4);
        this.password = resultSet.getString(5);
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int id) {
        this.userId = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String name) {
        this.lastName = name;
    }
    public String getEmail() { 
        return this.email; 
    } 
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getPassword() {
        return this.password; 
    }
    public void setPassword(String password) {
        this.password = password;
    }

}