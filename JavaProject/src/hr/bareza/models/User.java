/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

import java.util.Arrays;

/**
 *
 * @author Bareza
 */
public class User {
    
    private int id;
    private String username;
    private int password;
    private boolean administrator;

    public User(String username, String password, boolean administrator) {
        this.username = username;
        this.password = Arrays.hashCode(password.toCharArray());
        this.administrator = administrator;
    }

    public User(int id, String username, int password, boolean administrator) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
    }
    
    public User(int id, String username, String password, boolean administrator) {
        this.id = id;
        this.username = username;
        this.password = Arrays.hashCode(password.toCharArray());
        this.administrator = administrator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Arrays.hashCode(password.toCharArray());
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    
    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }
    
}