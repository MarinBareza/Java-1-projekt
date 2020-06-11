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
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Bareza
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Person implements Comparable<Person>{
    
    //public static List<Person> people;
    private static final String SEPARATOR = ",";
    //private static final String NAME_SEPARATOR = " ";
    
    @XmlAttribute
    private int id;
    @XmlElement (name = "firstname")
    private String firstName;
    @XmlElement (name = "lastname")
    private String lastName;

    public static List<Person> parseToList(String line) {
        List<Person> people = new ArrayList<>();
        String[] names = line.trim().split(SEPARATOR);
        
        for (String fullName : names) {
            if (fullName.trim().split(" ").length == 1) {
                people.add(new Person(fullName, ""));
            } else {
                String lastName = fullName.substring(fullName.lastIndexOf(" "));
                String firstName = fullName.substring(0, fullName.lastIndexOf(" "));
                people.add(new Person(firstName.trim(), lastName.trim()));
            }
        }
        return people;
    }

    public Person() {
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(int id, String firstName, String lastName) {
        this(firstName, lastName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return id == ((Person) obj).id;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " "+ lastName;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.valueOf(id).compareTo(other.id);
    }
    
}
