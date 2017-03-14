package com.scalka.movieswebservices.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.scalka.movieswebservices.model.Movie;

public class MovieXMLParser {
    static ArrayList<Movie> movieList = new ArrayList<>();
    public static ArrayList<Movie> parseFeed(String content){
        try {
            //checking if this is a tag that we are interested in
            boolean inDataItemTag = false;
            //Which XML tag are you currently in
            String currentTagName = "";
            // the Movie object we are constructing from the XML
            Movie movie = null;
            //implementing pull parser
            XmlPullParserFactory factory = null;
                factory = XmlPullParserFactory.newInstance();
            //new parser
            XmlPullParser parser = null;
                parser = factory.newPullParser();
            // passing xml content pulled from web service
            parser.setInput(new StringReader(content));
            //checking event type
            int eventType = parser.getEventType();
            // XMLPullParser generates events. Once for each start tag, end tag and for text events
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        // if starting a new movie create a new Movie object to start building it up.
                        if (currentTagName.equals("movie")) {
                            inDataItemTag = true;
                            movie = new Movie();
                            movieList.add(movie);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        // if leaving current movie
                        if (parser.getName().equals("movie")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if (inDataItemTag && movie != null) {
                            switch (currentTagName) {
                                case "Title":
                                    movie.setTitle(parser.getText());
                                    break;
                                case "Directors":
                                    movie.setDirectors(parser.getText());
                                    break;
                                case "IMDb" :
                                    movie.setRating(Double.parseDouble(parser.getText()));
                                    break;
                                case "Year" :
                                    movie.setYear(Integer.parseInt(parser.getText()));
                                case "Genres" :
                                    movie.setGenres(parser.getText());
                                case "Photo" :
                                    movie.setPhoto(parser.getText());
                                default:
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            // return the complete list that was generated above
            return movieList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
