/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

/**
 *
 * @author Bareza
 */
public class Administrator {
    
    private int id;
    private String username;
    private int password;

    public Administrator(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = String.valueOf(password).hashCode();
    }

    public Administrator(int id, String username, int password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
        this.password = String.valueOf(password).hashCode();
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Administrator) {
            return id == ((Administrator)obj).id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }
    
}
