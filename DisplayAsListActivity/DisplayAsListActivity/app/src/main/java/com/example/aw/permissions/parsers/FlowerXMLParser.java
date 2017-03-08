package com.example.aw.permissions.parsers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


// XmlPullParser - Built in feature of the Android SDK - can do a forward only read of XML data
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// Remember to import your class in the model package.
import com.example.aw.permissions.model.Flower;

public class FlowerXMLParser {

    public static List<Flower> parseFeed(String content) {

        try {

            // Establish Variables that you need to keep track of where you are
            // are you in a data item you care about
            boolean inDataItemTag = false;
            //Which XML tag are you current in
            String currentTagName = "";
            // the Flower object you are currently constructing from the XML
            Flower flower = null;
            // Full list of flowers as you pull them out of the XML
            List<Flower> flowerList = new ArrayList<>();


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
           // content is the complete XML content that was passed in from the calling program
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            // XMLPullParser generates events. Once for each start tag, end tag and for text events
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();

                        // if starting a new product create a new Flower object to start building it up.
                        if (currentTagName.equals("product")) {
                            inDataItemTag = true;
                            flower = new Flower();
                            flowerList.add(flower);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        // if leaving current product
                        if (parser.getName().equals("product")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (inDataItemTag && flower != null) {
                            switch (currentTagName) {
                                case "productId":
                                    flower.setProductId(Integer.parseInt(parser.getText()));
                                    break;
                                case "name":
                                    flower.setName(parser.getText());
                                    break;
                                case "instructions":
                                    flower.setInstructions(parser.getText());
                                    break;
                                case "category":
                                    flower.setCategory(parser.getText());
                                    break;
                                case "price" :
                                    flower.setPrice(Double.parseDouble(parser.getText()));
                                    break;
                                case "photo" :
                                    flower.setPhoto(parser.getText());
                                default:
                                    break;
                            }
                        }
                        break;
                }

                eventType = parser.next();

            }

            // return the complete list that was generated above
            return flowerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}

