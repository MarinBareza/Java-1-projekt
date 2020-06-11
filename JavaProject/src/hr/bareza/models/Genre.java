/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Bareza
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Genre implements Comparable<Genre>{
    
    @XmlAttribute
    private int id;
    private String name;
    
    
    private static final String SEPARATOR = ",";
    
    public static List<Genre> parseToList(String line) {
        List<Genre> genres = new ArrayList<>();
        String[] names = line.trim().split(SEPARATOR);
        
        for (String name : names) {
            Genre genre = new Genre(name);
            genres.add(genre);
        }
        
        return genres;
    }

    public Genre() {
    }

    public Genre(int id, String name) {
        this(name);
        this.id = id;
    }

    public Genre(String name) {
        name = String.valueOf(name.trim().charAt(0)).toUpperCase() + name.trim().substring(1);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = String.valueOf(name.trim().charAt(0)).toUpperCase() + name.trim().substring(1);
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Genre) {
            return ((Genre) obj).id == id;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public int compareTo(Genre other) {
        return Integer.valueOf(id).compareTo(other.id);
    }
    
}
