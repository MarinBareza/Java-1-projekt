/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.parser;

import hr.bareza.AdminForm;
import hr.bareza.parsers.ParserFactory;
import hr.bareza.models.Movie;
import hr.bareza.parsers.UrlConnectionFactory;
import hr.bareza.utils.FileUtils;
import hr.bareza.models.Genre;
import hr.bareza.models.Person;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Bareza
 */
public class RssParser {
    
    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final int TIMEOUT = 1000;
    private static final String REQUEST_METHOD = "GET";
    private static final String EXT = ".jpg";
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final Random RANDOM = new Random();
    
    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpsUrlConnection(RSS_URL, TIMEOUT, REQUEST_METHOD);
        XMLEventReader reader = ParserFactory.createStaxParser(con.getInputStream());
        
        Optional<TagType> tagType = Optional.empty();
        Movie movie = null;
        StartElement startElement = null;
        while (reader.hasNext()) {            
            XMLEvent event = reader.nextEvent();
            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    tagType = TagType.from(qName);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent()) {
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        switch (tagType.get()) {
                            case ITEM:
                                movie = new Movie();
                                movies.add(movie);
                                break;
                            case TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setTitle(data);
                                }
                                break;
                            case PUBLISHED_DATE:
                                if (movie != null && !data.isEmpty()) {
                                    LocalDateTime publishedDate = LocalDateTime.parse(data, DATE_FORMATTER);
                                    movie.setPublishedDate(publishedDate);
                                }
                                break;
                            case DESCRIPTION:
                                if (movie != null && !data.isEmpty()) {
                                    data = data.substring(data.lastIndexOf("\">") + 2, data.indexOf("<", data.lastIndexOf("\">") + 2));
                                    movie.setDescription(data);
                                }
                                break;
                            case ORIGINAL_TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setOriginalTitle(data);
                                }
                                break;
                            case DIRECTOR:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDirectors(Person.parseToList(data));
                                }
                                break;
                            case ACTOR:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setActors(Person.parseToList(data));
                                }
                                break;
                            case DURATION:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDuration(Integer.valueOf(data));
                                }
                                break;
                            case GENRE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setGenres(Genre.parseToList(data));
                                }
                                break;
                            case POSTER:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setPicturePath(downloadPicture(data));
                                }
                                break;
                            case LINK:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setLink(data);
                                }
                            break;
                        }
                    }
                break;
            }
        }
        return movies;
    }

    private static String downloadPicture(String url) throws IOException, IOException {
        
        url = url.replaceAll("http", "https");
        String ext = url.substring(url.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        String pictureName = "poster" + Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = AdminForm.POSTERS + File.separator + pictureName;
        
        FileUtils.downloadFromUrl(url, localPicturePath);
        
        return localPicturePath;
    }
    
    private enum TagType {

        ITEM("item"), 
        TITLE("title"), 
        PUBLISHED_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_TITLE("orignaziv"),
        DIRECTOR("redatelj"),
        ACTOR("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        POSTER("plakat"),
        LINK("link");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
        
    }
}
