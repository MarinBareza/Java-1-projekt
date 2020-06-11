/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Bareza
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Movie implements Comparable<Movie>{
    
    @XmlAttribute
    private int id;
    private String title;
    @XmlElement (name = "publisheddate")
    @XmlJavaTypeAdapter (PublishedDateAdapter.class)
    private LocalDateTime publishedDate;
    private String description;
    @XmlElement (name = "originaltitle")
    private String originalTitle;
    private int duration;
    @XmlElement (name = "picturepath")
    private String picturePath;
    private String link;
    
    @XmlElementWrapper
    @XmlElement (name = "actor")
    private List<Person> actors;
    
    @XmlElementWrapper 
    @XmlElement (name = "director")
    private List<Person> directors;
    
    @XmlElementWrapper 
    @XmlElement (name = "genre")
    List<Genre> genres;
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Movie() {
    }

    public Movie(String title, LocalDateTime publishedDate, String description, String originalTitle,
            int duration, String picturePath, String link) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.picturePath = picturePath;
        this.link = link;
        actors = new ArrayList<>();
        directors = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String originalTitle,
            int duration, String picturePath, String link) {
        this(title, publishedDate, description, originalTitle, duration, picturePath, link);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle.trim();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link.trim();
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }
    
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return id + " " + originalTitle + " " + publishedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            return ((Movie) obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public int compareTo(Movie other) {
        return Integer.valueOf(id).compareTo(other.id);
    }
     
}
