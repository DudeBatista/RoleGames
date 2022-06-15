package com.example.rolegames;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private LinearLayout lineNoticias;
    String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    lineNoticias = (LinearLayout) findViewById(R.id.LinearNoticias);

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        Bundle queryBundle =  new Bundle();
        queryBundle.putString("query", query);
        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }

        fetchSteam();

    }

    public void fetchSteam(){
        ConnectivityManager checkConnection = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = null;
        if(checkConnection != null){
            net = checkConnection.getActiveNetworkInfo();
        }
        if(net != null && net.isConnected()){
            if(query.length() == 0){
                Bundle args =new Bundle;
                args.putString("error","empty");
            }
            else{
                Bundle queryBundle = new Bundle();
                queryBundle.putString("query", query);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);
            }
        }
        else{

        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String query = "";
        if(args != null){
            query = args.getString("query");
        }
        return new fetchSteam(this, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONObject itemsObjeto = new JSONObject(data);
            JSONArray itemsArray = itemsObjeto.getJSONArray("items");
            int i = 0;
            int appid = 0;
            String contents =  null;
            String title = null;
            String author = null;

            while (i < itemsArray.length() && (author == null && contents == null)){
                JSONObject news = itemsArray.getJSONObject(i);
                JSONObject appnews = news.getJSONObject("appnews");
                    JSONArray arrayNews = appnews.getJSONArray("newsitem");
                        JSONObject noticias = arrayNews.getJSONObject(Integer.parseInt("noticias"));
                            JSONObject titulo = noticias.getJSONObject("titulo");
                            JSONObject autor = noticias.getJSONObject("autor");
                            JSONObject conteudo = noticias.getJSONObject("conteudo");
                try{
                    appid =appnews.getInt("id");
                    contents = conteudo.getString("texto");
                    title = titulo.getString("nome");
                    author = autor.getString("autores");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if(appid != 0 && contents != null && title != null && author != null ){

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}