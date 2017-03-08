package com.example.aw.permissions;

/**
 * Created by Anne on 22/01/2016.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpManager {

    // A URI is passed in from MainActivity
    // the content at this URI is returned
    public static String getData(String uri) {

        BufferedReader reader = null;

        // if all goes well the code in the try block is executed successfully
        try {
            URL url = new URL(uri);

            // open a connection to that URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            // the reader will continue to read in from the input stream till everything is read in
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // convert sb to a strign and return the lot the MainActivity for output to the UI
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Finally is important to avoid leaks
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

}
