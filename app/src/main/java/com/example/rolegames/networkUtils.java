package com.example.rolegames;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class networkUtils {
    private static final String LOG_TAG = networkUtils.class.getSimpleName();
    private static final String URL_STEAM = "GET https://api.steampowered.com/ISteamNews/GetNewsForApp/v2";
    private static final String STEAM_QUERY ="/";

    String steamJSON = null;
    static String url = null;
    static String searchApp(String query) {
        HttpURLConnection conexao = null;
        BufferedReader reader = null;
        String steamJSON = null;
        try {
            Uri buildURI = Uri.parse(URL_STEAM).buildUpon()
                    .appendPath(query)
                    .build();
            URL requestURL = new URL(buildURI.toString());
            url = buildURI.toString();

            conexao = (HttpURLConnection) requestURL.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            InputStream inputStream = conexao.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("/n");
            }
            if (builder.length() == 0) {
                return null;
            }
            steamJSON = builder.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conexao.disconnect();
        }
        return steamJSON;
    }


}
