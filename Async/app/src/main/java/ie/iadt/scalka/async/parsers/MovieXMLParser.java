package ie.iadt.scalka.async.parsers;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import ie.iadt.scalka.async.model.Movie;

public class MovieXMLParser {

    public static List<Movie> parseFeed(String content){

    try {
        // Establish Variables that you need to keep track of where you are
        // are you in a data item you care about
        boolean inDataItemTag = false;
        //Which XML tag are you current in
        String currentTagName = "";
        // the Flower object you are currently constructing from the XML
        Movie movie = null;
        // Full list of flowers as you pull them out of the XML
        List<Movie> movieList = new ArrayList<>();


        XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();

        XmlPullParser parser = null;
            parser = factory.newPullParser();

        // content is the complete XML content that was passed in from the calling program
        parser.setInput(new StringReader(content));

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
                            case "Rating" :
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
