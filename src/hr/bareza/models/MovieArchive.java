/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bareza
 */
@XmlRootElement (name = "moviearchive")
@XmlAccessorType ( XmlAccessType.FIELD)
public class MovieArchive {
    
    @XmlElementWrapper
    @XmlElement (name = "movie")
    List<Movie> movies;

    public MovieArchive() {
    }

    public MovieArchive(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
