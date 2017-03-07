package com.scalka.movieswebservices;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    public static String getData(String uri){
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            //open a connection to that URL
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;

            //the reader will continue to read in from the input stream till everything is read in
            while((line = reader.readLine()) != null ){
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        //finally is important to avoid leak
        finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
