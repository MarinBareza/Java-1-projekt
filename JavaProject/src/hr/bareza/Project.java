/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza;

import hr.bareza.dal.Repository;
import hr.bareza.dal.RepositoryFactory;
import hr.bareza.models.Movie;
import hr.bareza.models.MovieArchive;
import hr.bareza.parser.RssParser;
import hr.bareza.utils.JAXBUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Bareza
 */
public class Project {
    public static void main(String[] args) {
        
        try {
            /*try (FileWriter writer = new FileWriter("mojFile.txt")) {
            List<Movie> movies = XMLParser.parse();
            writeMovies(movies, writer);
            } catch (Exception ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            //loadMovies();
            
            /*List<Movie> movies = XMLParser.parse();
            JAXBUtils.save(new MovieArchive(movies), "movieArchive.xml");*/
        } catch (Exception ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*String password = "admin123";
        
        System.out.println(String.valueOf(password).hashCode());
        System.out.println(Arrays.hashCode(password.toCharArray()));*/
        
        //String[] COLUMN_NAMES = {"Title", "OriginalTitle", "PublishedDate", "Duration"};
        //System.out.println(COLUMN_NAMES.length);   

        //Repository repository = RepositoryFactory.getRepository();
        //Movie movie = repository.selectMovie(1);
        
        String pdfName = "AA.BB-CC-DD.zip";
        getId(pdfName);
    }

    private static void getId(String pdfName){
        String[]tokens = pdfName.split("-|\\.");
        for (String token : tokens) {
            System.out.println(token.toString());
        }
    }
    
    private static void writeMovies(List<Movie> movies, FileWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Movie movie : movies) {
            
            try {
                sb.append(movie.getTitle());
                sb.append("\n");
                movie.getActors().forEach(a -> sb.append(a.toString()));
                movie.getDirectors().forEach(d -> sb.append(d.toString()));
                sb.append("\n");
                sb.append("--------------------------------------");
                sb.append("\n");
            } catch (Exception e) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, e);
                sb.append("--------------------------------------");
                sb.append("\n");
            }
        }
        writer.write((sb.toString()));
        writer.close();
    }

    private static void loadMovies() throws IOException, XMLStreamException, Exception {
        List<Movie> movies = RssParser.parse();
        Repository repo = RepositoryFactory.getRepository();
        
        repo.createMovies(movies);
    }
}


