package com.example.rolegames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

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

    }
}